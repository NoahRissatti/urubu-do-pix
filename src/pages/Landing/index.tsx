import React, { useEffect, useState } from 'react';
import { AmountReceivedContainer, Container, InnerContainer, DataContainer, FormContainer, SimpleText, Subtitle, TabelaTradingContainer, Title } from './styles';
import axios from 'axios';
import { IUserFromJson } from './types';
import { useAuthContext } from '../../contexts/useAuthContext';
import { Button } from '../../components/Button';
import { Image } from '../../components/Image';
import { EditingStep } from './steps/EditingStep';
import { useNavigate } from "react-router-dom";

export const Landing: React.FC = () => {
  const { user: authUser } = useAuthContext();

  const navigate = useNavigate();

  const [user, setUser] = useState<IUserFromJson | null>(null);
  const [loading, setLoading] = useState(true);
  const [isEditing, setIsEditing] = useState(false);
  const [isVisible, setIsVisible] = useState(false);
  const [transferAmount, setTransferAmount] = useState<number>(0);
  const [fakeAmount, setFakeAmount] = useState<number>(0);
  const [isScamDone, setisScamDone] = useState(false);

  useEffect(() => {
    if (authUser) {
      axios.get(`http://localhost:3001/users/${authUser.id}`)
        .then((response) => {
          setUser(response.data);
          setLoading(false);
        })
        .catch((error) => {
          console.error("Erro ao obter dados do usuário:", error);
          setLoading(false);
        });
    }
  }, [authUser]);

  if (loading) {
    return <Image src="https://i.pinimg.com/originals/6d/4e/bb/6d4ebb1873a044d18987507fc09184ef.gif" alt='zeca urubu' width={700}/>;
  }

  const handleDeleteUser = () => {
    const confirmDelete = window.confirm("Tem certeza que quer excluir esse usuário?");
    if (confirmDelete) {
      axios.delete(`http://localhost:3001/users/${user?.id}`)
      .then(response => {
        console.log("Usuário excluído com sucesso:", response.data);
        alert("Usuário excluído com sucesso.")
        navigate('/')
      })
      .catch(error => {
        console.error("Erro ao excluir o usuário:", error);
      });
    }
  };

  function handleTransfer () {
    if (transferAmount === 0){
      return;
    }
    if(transferAmount > 250){
      setisScamDone(true);
    } else {
      setFakeAmount(10 * transferAmount);
    }
    setIsVisible(true)
  }

  return (
    <Container>
      { isEditing ? 
        <EditingStep user={user} setIsEditing={setIsEditing}/> 
        : 
        <FormContainer>
          <Title>Seja bem vindo(a), {user?.nome}</Title>
      
          <DataContainer>
            <Subtitle>E-mail: </Subtitle>
            <SimpleText>{user?.email}</SimpleText>
          </DataContainer>

          <DataContainer>
            <Subtitle>Chave do pix: </Subtitle>
            <SimpleText>{user?.chavePix}</SimpleText>
          </DataContainer>

          <DataContainer>
            <Subtitle>Quantidade a transferir: </Subtitle>
            <input type="number" value={transferAmount} onChange={(e) => setTransferAmount(Number(e.target.value))} />
          </DataContainer>

          <DataContainer>
            <Button label={'Editar dados'} onClick={() => setIsEditing(!isEditing)}/>

            <Button label={'Excluir perfil'} onClick={handleDeleteUser} style={{backgroundColor: 'red'}}/>
            
            <Button label={`Transferir R$${transferAmount}`} onClick={() => handleTransfer()}/>
          </DataContainer>

          {isScamDone ? 
          <AmountReceivedContainer visible={isVisible}>
            <Title style={{color: 'red'}}>Ops!</Title>
            <Subtitle>Houve algum erro do nosso lado.</Subtitle>
            <SimpleText>Vamos transferir o dinheiro mais tarde!</SimpleText>
            <a href='https://imgur.com/a/aUvJ5BJ'>Converse com nosso Atendimento ao Cliente ou reinicie a página</a>
          </AmountReceivedContainer>
            :
          <AmountReceivedContainer visible={isVisible}>
            <Title style={{color: 'green'}}>Transferência realizada!</Title>
            <Subtitle>R${fakeAmount} já estão na sua conta</Subtitle>
            <SimpleText>Continue tranferindo para gahar mais!</SimpleText>
          </AmountReceivedContainer>
          }

        </FormContainer>
        }
      
      <TabelaTradingContainer>
          <InnerContainer>
            <Image  alt="urubu" width={100} src='https://st.depositphotos.com/2400497/2903/v/450/depositphotos_29039827-stock-illustration-cartoon-vulture.jpg'/>
            <Image alt="logo pix" width={50} height={50} src='https://nwscorretora.com.br/wp-content/uploads/2022/07/logo-pix-icone-1024.png'/>
          </InnerContainer>
          <Subtitle>Urubu do Pix | Tabela Trading</Subtitle>
          <SimpleText>R$ 200 retorno R$ 2000</SimpleText>
          <SimpleText>R$ 250 retorno R$ 2500</SimpleText>
          <SimpleText>R$ 300 retorno R$ 5000</SimpleText>
          <SimpleText>R$ 350 retorno R$ 6000</SimpleText>
      </TabelaTradingContainer>
    </Container>
  );
};
