import styled from 'styled-components'

export const InputWrapper = styled.div`
  display: flex;
  flex-direction: column;
  margin-bottom: 16px;
  backgound-color: black;
`;

export const Label = styled.label`
  margin-bottom: 8px;
  font-weight: bold;
`;


export const Option = styled.div`
  margin: 2px;
  display: flex;
  flex-direction: row;
  justify-content: flex-start;
`

export const  Label2 = styled.label`
  margin-right: 10%;
`

export const Input = styled.input`
  backgound-color: red;
  padding: 8px;
  border: 1px solid #ccc;
  border-radius: 4px;
  outline: none;
`;