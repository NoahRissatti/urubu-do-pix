import React, { createContext, useContext, PropsWithChildren, useState } from 'react';

export interface IUser {
  id: number;
  email: string;
  senha: string;
}

export interface IAuthContextData {
  user: IUser | null;
  login: (userData: IUser) => void;
  logout: () => void;
}

const AuthContext = createContext<IAuthContextData>({} as IAuthContextData);

const AuthContextProvider: React.FC<PropsWithChildren> = ({ children }) => {
  const [user, setUser] = useState<IUser | null>(null);

  const login = (userData: IUser) => {
    setUser(userData);
  };

  const logout = () => {
    setUser(null);
  };

  return (
    <AuthContext.Provider value={{ user, login, logout }}>
      {children}
    </AuthContext.Provider>
  );
};

function useAuthContext(): IAuthContextData {
  const context = useContext(AuthContext);

  if (!context) {
    throw new Error('useAuthContext must be within a ContextProvider');
  }

  return context;
}

export { AuthContextProvider, useAuthContext };
