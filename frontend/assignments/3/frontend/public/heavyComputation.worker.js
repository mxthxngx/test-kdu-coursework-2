self.onmessage = (event) => {
    const apiUrl = 'https://kdu-automation.s3.ap-south-1.amazonaws.com/mini-project-apis/all-stocks-transactions.json';
  
    fetch(apiUrl)
      .then(response => response.json())
      .then(data => {
        const results = findBestTimeToBuySell(data);
        self.postMessage(results);
      })
      .catch(error => {
        console.error('Error fetching data:', error);
        self.postMessage({ error: 'Error fetching data' });
      });
  };
  
  function findBestTimeToBuySell(data) {
    const results = [];

    data.forEach(stock => {
        let maxProfit = 0;
        let bestBuyDate = "";
        let bestSellDate = "";
        let buyPrice = Infinity;
        let sellPrice = Infinity;

        for (let i = 0; i < stock.data.length; i++) {
            const buyTransaction = stock.data[i];
            const buyDate = buyTransaction.date;
            const buyPrices = buyTransaction.prices;

            for (let j = i + 1; j < stock.data.length; j++) {
                const sellTransaction = stock.data[j];
                const sellDate = sellTransaction.date;
                const sellPrices = sellTransaction.prices;

                if (sellDate > buyDate) {
                    for (const buyPriceItem of buyPrices) {
                        for (const sellPriceItem of sellPrices) {
                            if(buyPriceItem<0)
                            {
                                console.log("negative!",buyPriceItem,sellPriceItem,buyDate,sellDate,buyTransaction,sellTransaction);
                            }
                            const potentialProfit = sellPriceItem - buyPriceItem;
                            if (potentialProfit > maxProfit) {
                                maxProfit = potentialProfit;
                                bestBuyDate = buyDate;
                                bestSellDate = sellDate;
                                buyPrice = buyPriceItem;
                                sellPrice = sellPriceItem;
                            }
                        }
                    }
                }
            }
        }

        const profit = maxProfit > 0 ? maxProfit : 0;
        results.push({
            company: stock.company,
            symbol: stock.symbol,
            bestBuyDate,
            buyPrice,
            bestSellDate,
            sellPrice,
            profit
        });
    });

    console.log("result: ", results);

    return results;
}
