import { styled } from "@mui/material";
interface BoxProps 
{
    bgColor?: string;
    borderColor?: string;
}

const BoxOutline = styled('div')<BoxProps>(({  bgColor, borderColor }) => ({
  display: 'flex',
  padding: '.5rem',
  justifyContent: 'center',
  alignItems: 'center',
  margin:' .1rem',
  backgroundColor: bgColor || 'white',
  border: `1px solid ${borderColor || 'black'}`,
  
}));

export default BoxOutline;
