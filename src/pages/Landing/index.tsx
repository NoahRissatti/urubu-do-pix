import React, { useEffect, useState } from 'react';
import { Container, Title } from './styles';
import axios from 'axios';
import { IUserFromJson } from './types';
import { useAuthContext } from '../../contexts/useAuthContext';

export const Landing: React.FC = () => {
  const { user: authUser } = useAuthContext();
  const [user, setUser] = useState<IUserFromJson | null>(null);
  const [loading, setLoading] = useState(true);

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

  return (
    <Container>
      <Title>Seja bem vindo(a), {user?.nome}</Title>
    </Container>
  );
};
