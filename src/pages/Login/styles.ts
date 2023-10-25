import styled from 'styled-components'

export const Container = styled.div`
display: flex;
justify-content: center;
align-items: center;
height: 100vh;
`

export const Title = styled.text`
font-size: 2rem;
font-weight: bold;
`

export const Content = styled.div`
display: flex;
padding: 5rem 2rem 5rem 2rem;
flex-direction:column;
background-color: #f7fafe;
width: 30%;
border-radius: 5px;
border: 1px solid #ddecfa;
gap: 2rem;
`

export const ContainerButtons = styled.div`
display: flex;
flex-direction:row;
width: 100%;
gap: 2rem;
justify-content: flex-end;
`

export const ContainerTitle = styled.div`
display: flex;
flex-direction:row;
`

export const ContainerBody = styled.div`
display: flex;
flex-direction:column;
`

