export interface HistoryItem 
{
    date: string,
    price: number,
    type:TransactionType,
    quantity:number
}
export enum TransactionType {
    BUY = "Buy",
    SELL = "Sell"
}