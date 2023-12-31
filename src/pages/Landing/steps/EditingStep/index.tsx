// External Libraries
import React, { useEffect, useState } from 'react'

// Components

// Styles
import { Container, DataContainer, Title } from './styles'
import { TitledInput } from '../../../../components/TitledInput'
import axios from 'axios'
import { IUserFromJson } from '../../types'
import { Button } from '../../../../components/Button'

interface Props {
  user: IUserFromJson | null
  setIsEditing: React.Dispatch<React.SetStateAction<boolean>>;
}

export const EditingStep: React.FC<Props> = ({ user, setIsEditing }) => {
  
  const [formData, setFormData] = useState({
    nome: "",
    email: "",
    senha: "",
    chavePix: ""
  });

  const handleChange = (e: React.ChangeEvent<HTMLInputElement>) => {
    const { name, value } = e.target;
    setFormData(prevState => ({
      ...prevState,
      [name]: value,
    }));
  };

  const handleEdit = () => {
    if (
      formData.nome.trim() === '' ||
      formData.email.trim() === '' ||
      formData.senha.trim() === '' ||
      formData.chavePix.trim() === ''
    ) {
      alert('Por favor, preencha todos os campos antes de confirmar a edição.');
      return;
    }

    axios
      .put(`http://localhost:3001/users/${user?.id}`, formData)
      .then(response => {
        console.log('Dados do usuário atualizados com sucesso:', response.data);
        setIsEditing(false)
      })
      .catch(error => {
        console.error('Erro ao atualizar os dados do usuário:', error);
      });
  };

  return (
    <Container>
      <Title>Editando os dados</Title>

      <TitledInput
          label={'Nome'}
          placeholder='Insira seu novo nome'
          name='nome'
          type='text'
          value={formData.nome}
          onChange={handleChange}
          style={{ width: '20rem'}}
        />

      <TitledInput
          label={'E-mail'}
          placeholder='Insira seu novo e-mail'
          name='email'
          type='email'
          value={formData.email}
          onChange={handleChange}
          style={{ width: '20rem'}}
        />

    	<TitledInput
          label={'Senha'}
          placeholder='Insira sua nova senha'
          name='senha'
          type='password'
          value={formData.senha}
          onChange={handleChange}
          style={{ width: '20rem'}}
        />

      <TitledInput
          label={'Chave pix'}
          placeholder='Insira sua nova chave do pix'
          name='chavePix'
          type='text'
          value={formData.chavePix}
          onChange={handleChange}
          style={{ width: '20rem'}}
        />

        <DataContainer>
          <Button label={'Confirmar edição'} onClick={handleEdit}/>

          <Button label={'Cancelar'} style={{backgroundColor: 'red'}} onClick={() => setIsEditing(false)}/>
        </DataContainer>
    </Container>
  
  )
}
