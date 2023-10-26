import styled from 'styled-components'

export const SuccessModalContainer = styled.div`
  position: fixed;
  top: 0;
  left: 0;
  width: 100%;
  height: 100%;
  background-color: rgba(0, 0, 0, 0.5);
  display: flex;
  justify-content: center;
  align-items: center;
  z-index: 999;
`;

export const SuccessModalContent = styled.div`
  background-color: #fff;
  padding: 20px;
  border-radius: 5px;
  box-shadow: 0 2px 4px rgba(0, 0, 0, 0.2);
  text-align: center;
  max-width: 300px;
`;

export const SuccessModalTitle = styled.h2`
  font-size: 24px;
  margin-bottom: 10px;
`;

export const SuccessModalText = styled.p`
  font-size: 16px;
  margin-bottom: 20px;
`;

export const SuccessModalButton = styled.button`
  background-color: #007bff;
  color: #fff;
  border: none;
  padding: 10px 20px;
  border-radius: 5px;
  cursor: pointer;
  font-size: 16px;
`;