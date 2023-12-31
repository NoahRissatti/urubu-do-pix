import styled from 'styled-components'

export const ButtonGeneric = styled.button`
  padding: 10px 20px;
  width: fit-content;
  background-color: #007bff;
  color: #fff;
  border: none;
  border-radius: 4px;
  cursor: pointer;
  transition: background-color 0.3s;

  &:hover {
    background-color: #0056b3;
  }
`;