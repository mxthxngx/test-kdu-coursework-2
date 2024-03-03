import { styled } from "@mui/material";

interface FlexDivContainerProps {
  justifyCon?:string;
  width?: string; 
}

export const FlexDivContainer = styled('div')<FlexDivContainerProps>(({ width ,justifyCon}) => ({
  display: 'flex',
  flexDirection: 'row',
  justifyContent: justifyCon || 'space-evenly',
  width: width || 'auto',
}));
