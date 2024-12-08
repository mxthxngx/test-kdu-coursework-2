import { TableCell, TableRow } from "@mui/material";
import React from "react";

interface MemoizedListItemProps {
  index: number;
  style: React.CSSProperties;
  transactions: any[];
}

export const MemoizedListItem: React.FC<MemoizedListItemProps> = React.memo(
  ({ index, style, transactions }) => (
    <TableRow key={index} style={style}> 
      <TableCell style={{ width: "50vw" }}>
        {transactions[index].stockname}
      </TableCell>
      <TableCell>{transactions[index].symbol}</TableCell>
      <TableCell>{transactions[index].price}</TableCell>
      <TableCell>
        {new Date(transactions[index].date).toLocaleTimeString()}
      </TableCell>
      <TableCell>
        {transactions[index].type === "Passed" ? (
          <div
            style={{
              width: "10px",
              height: "10px",
              borderRadius: "50%",
              backgroundColor: "green",
            }}
          />
        ) : (
          <div
            style={{
              width: "10px",
              height: "10px",
              borderRadius: "50%",
              backgroundColor: "red",
            }}
          />
        )}
      </TableCell>
    </TableRow>
  )
);
