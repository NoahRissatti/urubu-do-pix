// External Libraries
import React from "react";

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

import axios from 'axios';

const apiUrl = 'http://localhost:3001';

axios.get(`${apiUrl}/posts`)
  .then(response => {
    // Faça algo com os dados
  })
  .catch(error => {
    // Trate os erros
  });

export const Login: React.FC = () => {
  return (
    <Container>
      <Content>
        <ContainerTitle>
          <Title>Entrar ou cadastrar-se no Urubu do Pix</Title>
        </ContainerTitle>

        <ContainerBody>
          <TitledInput label={"Email"} placeholder="Insira seu email..." />
          <TitledInput label={"Senha"} placeholder="Insira sua senha" />
          <ContainerButtons>
            <Link to={`register`}>
              <Button label={"Cadastrar-se"} />
            </Link>
            <Button label={"Login"} />
          </ContainerButtons>
        </ContainerBody>
      </Content>
    </Container>
  );
};
