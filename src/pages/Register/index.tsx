// External Libraries
import React, { useState } from "react";

// Components

// Styles
import {
  Container,
  ContainerBody,
  ContainerButtons,
  Content,
  Title,
} from "./styles";
import { TitledInput } from "../../components/TitledInput";
import { Button } from "../../components/Button";
import { Link } from "react-router-dom";
import axios from "axios";

export const Register: React.FC = () => {
  const [formData, setFormData] = useState({
    nome: "",
    email: "",
    senha: "",
    chavePix: "",
  });

  const handleChange = (e: React.ChangeEvent<HTMLInputElement>) => {
    const { name, value } = e.target;
    setFormData({
      ...formData,
      [name]: value,
    });
  };

  const handleSubmit = (e: React.FormEvent) => {
    e.preventDefault();
    axios
      .post("http://localhost:3001/users", formData)
      .then((response) => {
        console.log("Dados enviados com sucesso:", response.data);
      })
      .catch((error) => {
        console.error("Erro ao enviar os dados:", error);
      });
  };
  return (
    <Container>
      <Content>
        <Title>Cadastre-se para utilizar o Urubo do Pix</Title>
        <ContainerBody>
          <form onSubmit={handleSubmit}>
            <TitledInput
              label={"Nome"}
              placeholder="Insira seu nome"
              name="nome"
              value={formData.nome}
              onChange={handleChange}
            />
            <TitledInput
              label={"Email"}
              placeholder="Insira seu email"
              name="email"
              value={formData.email}
              onChange={handleChange}
            />
            <TitledInput
              label={"Senha"}
              placeholder="Insira sua senha"
              name="senha"
              value={formData.senha}
              onChange={handleChange}
            />
            <TitledInput
              label={"Chave do Pix"}
              placeholder="Insira sua chave do Pix"
              name="chavePix"
              value={formData.chavePix}
              onChange={handleChange}
            />
            <ContainerButtons>
              <Link to={`/`}>
                <Button label={"Cancelar"} />
              </Link>
              <button type="submit">Cadastrar-se</button>
            </ContainerButtons>
          </form>
        </ContainerBody>
      </Content>
    </Container>
  );
};
