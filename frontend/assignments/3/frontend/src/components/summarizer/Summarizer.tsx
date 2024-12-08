import { useEffect, useRef, useState } from "react";
import { Box, LinearProgress, Typography } from "@mui/material";
import { FlexDivContainer } from "../utils/ui/layout/FlexDivContainer";

/**
 * Event handler for worker messages.
 *
 * @param {Event} event - the message event
 * @return {void} 
 */
export function Summarizer() {
  const workerRef = useRef<Worker | null>(null);
  const [result, setResult] = useState<any>(null);
  const [loading, setLoading] = useState<boolean>(true);

  useEffect(() => {
    workerRef.current = new Worker('heavyComputation.worker.js');

    /**
     * Event handler for worker messages.
     *
     * @param {Event} event - the message event
     * @return {void} 
     */
    workerRef.current.onmessage = (event) => {
      console.log(event.data);
      setResult(event.data);
      setLoading(false); 
    };

    /**
     * Set up an error handler for the worker reference.
     *
     * @param {Event} event - the error event
     * @return {void} 
     */
    workerRef.current.onerror = (event) => {
      console.error(event);
      setLoading(false);
    };

    workerRef.current.postMessage({});

    return () => {
      if (workerRef.current) {
        workerRef.current.terminate();
      }
    };
  }, []);

  return (
    loading? (
      <LinearProgress />
    ):(
    <Box p={4} style={{ margin: '2rem', color: 'white' }}>
      {loading ? ( 
        <LinearProgress />
      ) : result ? (
        result.map((stock: any, index: number) => (
          <Box key={index} bgcolor={stock.profit > 0 ? "#1871c2" : "lightcoral"} p={2} mt={2} borderRadius={8} >
            <FlexDivContainer justifyCon="space-between">
              <div>
                <Typography variant="h6" gutterBottom>
                  {stock.company}
                </Typography>
                <Typography variant="body1" gutterBottom>
                  Symbol: {stock.symbol}
                </Typography>
                <Typography variant="body1" gutterBottom>
                  Profit margin: {stock.profit}
                </Typography>
              </div>
              <div style={{ display: 'flex', flexDirection: 'column', justifyContent: 'space-between' }}>
                <Typography variant="body1" gutterBottom>
                  Buy {stock.buyPrice} on {stock.bestBuyDate}
                </Typography>
                <Typography variant="body1" gutterBottom>
                  Sell {stock.sellPrice} on {stock.bestSellDate}
                </Typography>
              </div>
            </FlexDivContainer>
          </Box>
        ))
      ) : (
        <Typography variant="body1">No data available</Typography>
      )}
    </Box>
    )
  );
}
