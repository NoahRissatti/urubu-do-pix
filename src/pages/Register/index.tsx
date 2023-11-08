// External Libraries
import React, { useState, useTransition } from "react";

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
import { RadioGroup } from "../../components/RadioInput";
import { Button } from "../../components/Button";
import { Link } from "react-router-dom";
import axios from "axios";
import { SuccessModal } from "../../components/SuccessModal";
import e from "express";

export const Register: React.FC = () => {
  const [formData, setFormData] = useState({
    nome: "",
    email: "",
    senha: "",
    chavePix: "",
    tipoChave: ""
  });
  const [showSuccessModal, setShowSuccessModal] = useState(false);
  
  const isCpfValid = (cpf: string): boolean => {
    const cpfClean = cpf.replace(/[^\d]/g, '');  
    
    if (cpfClean.length !== 11) {
      return false;
    }
    
    if (/^(\d)\1{10}$/.test(cpfClean)) {
      return false;
    }
    
    const cpfArray = cpfClean.split('').map(Number);
    const firstVerifier = cpfArray.slice(0, 9);
    const secondVerifier = cpfArray.slice(0, 10);
  
    const calculateDigit = (arr: number[]) => {
      let total = 0;
      let multiplier = arr.length + 1;
      for (const digit of arr) {
        total += digit * multiplier;
        multiplier--;
      }
      const remainder = total % 11;
      return remainder < 2 ? 0 : 11 - remainder;
    };
    
    const firstDigit = calculateDigit(firstVerifier);
    const secondDigit = calculateDigit(secondVerifier);
    
    return firstDigit === cpfArray[9] && secondDigit === cpfArray[10];
  }

  
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

  async function userExists(email: string) {
  return axios
    .get("http://localhost:3001/users")
    .then(function (response) {
      return response.data.find(function (usuario: any) {
        return usuario.email === email;
      });
    })
    .catch(function (error) {
      console.error("Erro ao enviar os dados:", error);
    });
}
  
  
  const handleSubmit = async (e: React.FormEvent) => {
    e.preventDefault();
    
    if (Object.values(formData).some((value) => value === "")) {
      alert("Por favor, preencha todos os campos antes de enviar.");
      return;
    }
    
    if (!isEmailValid(formData.email)) {
      alert("Por favor, insira um endereço de e-mail válido.");
      return;
    }
    
    if((formData.tipoChave === "CPF" && !isCpfValid(formData.chavePix)) || 
    (formData.tipoChave === "Email" && !isEmailValid(formData.chavePix))) {
      alert("Por favor, insira uma chave pix válida.");
      return;
    }
    
    if (await userExists(formData.email)){
      console.log("aasda")
      axios
      .post("http://localhost:3001/users", formData)
      .then((response) => {
        console.log("Dados enviados com sucesso:", response.data);
        setShowSuccessModal(true);
      })
      .catch((error) => {
        console.error("Erro ao enviar os dados:", error);
      });
    }
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
                <RadioGroup
                  label="Selecione o tipo de chave pix"
                  options={['CPF', 'Email']}
                  name="radioOption"
                  onChange={(e) => formData.tipoChave = e.target.value}
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
