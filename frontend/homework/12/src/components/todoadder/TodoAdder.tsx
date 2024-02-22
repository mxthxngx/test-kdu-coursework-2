import React, { useState } from 'react';
import './style.css';
import { Items } from '../items/Items';
import { TodoInterface } from '../../TodoInterface';
import { Header } from '../header/Header';
interface IItemsListContext
{
itemList: TodoInterface[];
setItemList : React.Dispatch<React.SetStateAction<TodoInterface[]>>
}

interface IItemInputContext
{
itemInput: string;
setInputItem : React.Dispatch<React.SetStateAction<string>>
}

interface IItemContext
{
item: string;
setItem : React.Dispatch<React.SetStateAction<string>>
}

export const ItemInputContext = React.createContext<IItemInputContext>({
  itemInput: "",
  setInputItem: () => { }
});

export const ItemsListContext = React.createContext<IItemsListContext>({
  itemList: [],
  setItemList: () => { }
});

export const ItemContext = React.createContext<IItemContext>({
  item: "",
  setItem: () => { }
})
export function TodoAdder() {
  const [item, setItem] = useState(""); 
  const [itemList, setItemList] = useState<TodoInterface[]>([]);
  const [itemtInput,setInputItem] = useState("");
  const addItem = () => {
    if (item !== "") {
      setItem(""); 
      setItemList([...itemList, { id: Date.now(), todo: item, isDone: false }])
    }
  }

  const handleInputChange = (event: React.ChangeEvent<HTMLInputElement>) => {
    setItem(event.target.value); 
  }

  return (
    <ItemInputContext.Provider value={{ itemInput: itemtInput, setInputItem }}>
      <ItemContext.Provider value={{ item, setItem }}>
        <ItemsListContext.Provider value = {{itemList,setItemList}}>
      <div className='main-container'>
            <Header />
            <div className = "body">
          <div className="todo-header">
            <h2>Add Items</h2>
            <div className="todo-input">
              <input type="text" value={item} onChange={handleInputChange} />
              <button className="submit-btn" onClick={addItem}>Submit</button>
            </div>
              <Items />

          </div>
          </div>
          </div>
          </ItemsListContext.Provider>
          </ItemContext.Provider>
      </ItemInputContext.Provider>
    
  );
}
