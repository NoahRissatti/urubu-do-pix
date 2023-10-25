// External Libraries
import React from "react";

// Components

// Styles
import { Container, ContainerButtons, Content } from "./styles";
import { TitledInput } from "../../components/TitledInput";
import { Button } from "../../components/Button";

export const Login: React.FC = () => {
  return (
    <Container>
      <Content>
        <TitledInput label={"Email"} />
        <TitledInput label={"Senha"} />
        <ContainerButtons>
          <Button label={"Cadastrar-se"} />
          <Button label={"Login"} />
        </ContainerButtons>
      </Content>
    </Container>
  );
};
