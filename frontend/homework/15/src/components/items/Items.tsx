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
    const cssStyles: { [key: string]: React.CSSProperties } = {
      itemContainer: {
        textAlign: 'center',
        marginTop: '20px',
      },
      noItems: {
        color: 'gray',
      },
      todoItem: {
        display: 'flex',
        justifyContent: 'space-between',
        border: '1px solid gainsboro',
        borderRadius: '2px',
        padding: '10px',
        marginTop: '10px',
      },
      li:{
        listStyle:'none'
      },
      crossBtn: {
        padding: '5px',
        borderRadius: '5px',
        backgroundColor: 'red',
        color: 'white',
        border: 'none',
        cursor: 'pointer',
      },
      ul:
      {
        margin:'0',
        padding:'0'
      }
    };
  
    return (
      <div style={cssStyles.itemContainer} className="item-container">
        <h2>Items</h2>
        {filteredItems.length === 0 ? (
          <p style={cssStyles.noItems}>No Items found</p>
        ) : (
          <ul style={cssStyles.ul}>
            {filteredItems.map((item) => (
              <div style={cssStyles.todoItem} key={item.id}>
                <li style={cssStyles.li}>{item.todo}</li>
                <button style={cssStyles.crossBtn} onClick={() => handleDelete(item.id)}>x</button>
              </div>
            ))}
          </ul>
        )}
      </div>
    );
}
