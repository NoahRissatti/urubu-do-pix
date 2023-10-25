// External Libraries
import React from "react";

// Components

// Styles
import { Container, Content, Title } from "./styles";
import { TitledInput } from "../../components/TitledInput";

export const Register: React.FC = () => {
  return (
    <Container>
      <Content>
        <Title>Cadastre-se para utilizar o Urubo do Pix</Title>
        <TitledInput label={"Nome"} placeholder="Insira seu nome" />
        <TitledInput label={"Email"} placeholder="Insira seu email" />
        <TitledInput label={"Senha"} placeholder="Insira sua senha" />
        <TitledInput label={"Chave do Pix"} placeholder="Insira sua chave do pix" />
      </Content>
    </Container>
  );
};
