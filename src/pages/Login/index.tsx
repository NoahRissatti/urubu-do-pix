// External Libraries
import React, { useEffect, useState } from "react";
// Components

// Styles
import {
  Container,
  ContainerBody,
  ContainerButtons,
  ContainerTitle,
  Content,
  Title,
} from "./styles";
import { TitledInput } from "../../components/TitledInput";
import { Button } from "../../components/Button";
import { Link } from "react-router-dom";
import axios from "axios";
import { IUser } from "./types";
import { useAuthContext } from "../../contexts/useAuthContext";
import { useNavigate } from "react-router-dom";

export const Login: React.FC = () => {

  const { login } = useAuthContext();

  const navigate = useNavigate();

  const [formData, setFormData] = useState({
    email: "",
    senha: "",
  });

  const [users, setUsers] = useState<IUser[]>([]);

  const handleChange = (e: React.ChangeEvent<HTMLInputElement>) => {
    const { name, value } = e.target;
    setFormData({
      ...formData,
      [name]: value,
    });
  };

  const handleLogin = () => {
    if (formData.email === '' || formData.senha === '') {
      alert('Por favor, preencha todos os campos antes de fazer o login.');
      return;
    }

    const user = users.find(
      (u) => u.email === formData.email && u.senha === formData.senha
    );

    if (user) {
      login(user)
      navigate('/landing')
    } else {
      alert('Email ou senha incorretos. Tente novamente.');
    }
  };
 

  useEffect(() => {
    axios.get('http://localhost:3001/users').then((response) => {
      setUsers(response.data);
    });
  }, []);

  return (
    <Container>
      <Content>
        <ContainerTitle>
          <Title>Entrar ou cadastrar-se no Urubu do Pix</Title>
        </ContainerTitle>

        <ContainerBody>
          <TitledInput
            label={'Email'}
            placeholder='Insira seu email...'
            name='email'
            value={formData.email}
            onChange={handleChange}
          />
          <TitledInput
            label={'Senha'}
            placeholder='Insira sua senha'
            name='senha'
            type='password'
            value={formData.senha}
            onChange={handleChange}
          />
          <ContainerButtons>
            <Link to={'register'}>
              <Button label={'Cadastrar-se'} />
            </Link>

            <Button label={"Login"} onClick={handleLogin}/>
          </ContainerButtons>
        </ContainerBody>
      </Content>
    </Container>
  );
};
