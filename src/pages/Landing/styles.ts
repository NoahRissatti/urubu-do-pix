import styled from 'styled-components'

interface Props {
    visible?: boolean
}

export const Container = styled.div`
    display: flex;
    flex-direction: row;
    align-items: center;

    
    @media screen and (max-width: 600px) {
        flex-direction: column-reverse;
      }
`


export const InnerContainer = styled.div`
    display: flex;
    flex-direction: row;
    align-items: center;
`

export const FormContainer = styled.div`
    display: flex;
    flex-direction: column;
    padding: 2rem;
    gap: 1rem;
    background-color: white;
`

export const DataContainer = styled.div`
    display: flex;
    flex-direction: row;
    gap: 0.5rem;

`

export const AmountReceivedContainer = styled.div<Props>`
    display: ${({ visible }) => visible ? 'flex' : 'none'};
    flex-direction: column;
    gap: 0.5rem;
    

`

export const TabelaTradingContainer = styled.div`
    display: flex;
    flex-direction: column;
    // padding: 2rem;
    // margin-left: 5rem;
    row-gap: 0.5rem;


`

export const Title = styled.text`
    font-size: 1.5rem;
    font-weight: bold;
`

export const Subtitle = styled.text`
    font-size: 1rem;
    font-weight: 600;
`

export const SimpleText = styled.text`
    font-size: 1rem;
    font-weight: 400;
`