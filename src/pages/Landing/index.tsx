import React, { useEffect, useState } from 'react';
import { Container, DataContainer, SimpleText, Subtitle, Title } from './styles';
import axios from 'axios';
import { IUserFromJson } from './types';
import { useAuthContext } from '../../contexts/useAuthContext';
import { Button } from '../../components/Button';
import { EditingStep } from './steps/EditingStep';
import { useNavigate } from "react-router-dom";

export const Landing: React.FC = () => {
  const { user: authUser } = useAuthContext();

  const navigate = useNavigate();

  const [user, setUser] = useState<IUserFromJson | null>(null);
  const [loading, setLoading] = useState(true);
  const [isEditing, setIsEditing] = useState(false);

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
    return <div>Carregando...</div>;
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

  return (
    <Container>
      { isEditing ? 
        <EditingStep user={user} setIsEditing={setIsEditing}/> 
        : 
        <>
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
            <Button label={'Editar dados'} onClick={() => setIsEditing(!isEditing)}/>

            <Button label={'Excluir perfil'} onClick={handleDeleteUser} style={{backgroundColor: 'red'}}/>
          </DataContainer>
        </>
        }
      
    </Container>
  );
};