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
import { SuccessModal } from "../../components/SuccessModal";

export const Register: React.FC = () => {
  const [formData, setFormData] = useState({
    nome: "",
    email: "",
    senha: "",
    chavePix: "",
  });
  const [showSuccessModal, setShowSuccessModal] = useState(false);

  const handleChange = (e: React.ChangeEvent<HTMLInputElement>) => {
    const { name, value } = e.target;
    setFormData({
      ...formData,
      [name]: value,
    });
  };

  const isEmailValid = (email: string) => {
    const emailRegex = /^[A-Z0-9._%+-]+@[A-Z0-9.-]+\.[A-Z]{2,}$/i;
    return emailRegex.test(email);
  };

  const handleSubmit = (e: React.FormEvent) => {
    e.preventDefault();

    if (Object.values(formData).some((value) => value === "")) {
      alert("Por favor, preencha todos os campos antes de enviar.");
      return;
    }

    if (!isEmailValid(formData.email)) {
      alert("Por favor, insira um endereço de e-mail válido.");
      return;
    }

    axios
      .post("http://localhost:3001/users", formData)
      .then((response) => {
        console.log("Dados enviados com sucesso:", response.data);
        setShowSuccessModal(true);
      })
      .catch((error) => {
        console.error("Erro ao enviar os dados:", error);
      });
  };
  
  return (
    <Container>
      <Content>
        <Title>Cadastre-se para utilizar o Urubu do Pix</Title>
        <ContainerBody>
          <form onSubmit={handleSubmit}>
            {showSuccessModal ? (
              <SuccessModal onClose={() => setShowSuccessModal(false)} />
            ) : (
              <>
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
              </>
            )}
          </form>
        </ContainerBody>
      </Content>
    </Container>
  );
};
