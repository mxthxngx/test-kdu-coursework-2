import "./style.css";
import { useDispatch, useSelector } from 'react-redux';
import { RootState } from '../redux/Store';
import { TodoInterface } from '../../TodoInterface';
import { setItemList } from '../redux/ItemSlice';
export function Items() {
  const reduxDispatch = useDispatch();
  const itemList = useSelector((state:RootState)=>state.adder.itemList)
  const setItemListHandler = (itemList:TodoInterface[]) => {
    reduxDispatch(setItemList(itemList))
  }
  const { itemInput } = useSelector((state:RootState)=>state.adder)
  const handleDelete = (id: number) => {
        setItemListHandler(itemList.filter((item) => item.id !== id))
    }
    let filteredItems = itemList;
    console.log("inputInputList: ",itemInput)
    if(itemInput.trim()!=='')
    {
        filteredItems=itemList.filter((item)=> item.todo.toLowerCase().includes(itemInput.toLowerCase()))
        console.log(filteredItems)
    }
    return (
        
    <div className="item-container">
      <h2>Items</h2>
      {filteredItems.length === 0?<ul>No Items found</ul>
      :
      <ul>
      {filteredItems.map((item) => (
        <div className="todo-item" key={item.id}>
          <li>{item.todo}</li>
          <button className='cross-btn' onClick={() => handleDelete(item.id)}>x</button>
        </div>
      ))}
    </ul>
      }
     
    </div>
  )
}
