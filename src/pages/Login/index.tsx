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

export const Login: React.FC = () => {
  return (
    <Container>
      <Content>
        <ContainerTitle>
          <Title>Entrar ou cadastrar-se no Urubu do Pix</Title>
        </ContainerTitle>

        <ContainerBody>
          <TitledInput label={"Email"} placeholder="Insira seu email..."/>
          <TitledInput label={"Senha"} placeholder="Insira sua senha"/>
          <ContainerButtons>
            <Button label={"Cadastrar-se"} />
            <Button label={"Login"} />
          </ContainerButtons>
        </ContainerBody>
      </Content>
    </Container>
  );
};
