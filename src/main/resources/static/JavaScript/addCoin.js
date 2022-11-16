const cookieObj = document.cookie.split(`;`).map(cookie=> cookie.split('=')).reduce((accumulator,[key,value])=>
({...accumulator,[key.trim()]: decodeURIComponent(value)}),
{});
console.log(cookieObj)
console.log(cookieObj)

const baseUrl = "http://localhost:8080"
const headers= {
    'Content-Type':'application/json',
    'Access-Control-Allow-Origin':'*'
}

const delay = ms=>new Promise(res => setTimeout(res,ms));

const main = document.getElementById("main")
const greeting = document.getElementById("greeting")
const coinsTable=document.getElementById("coins-table")
const getAllCoinsBtn=document.getElementById("getAllCoins")
const addWatchlistBtn = document.getElementById("addWatchlist")
const getWatchlistBtn = document.getElementById("getWatchlist")
const addCoinsBtn=document.getElementById("addCoin-btn")
const tbody = document.getElementById("tbody")
const body = document.getElementById("body")
const footer = document.getElementById("coins")

greeting.innerHTML=`
Edit Watchlist
`;

//Watchlist
const getWatchlistById = async (id)=>{
    let response = await fetch(`http://localhost:8080/watchlists/${id}`,{
        method:"GET",
        headers:headers
    }).catch(err=>console.error(err))

    let responseArr = await response.json()
    tbody.innerHTML=``
    // console.log(responseArr.coinSet)
    responseArr.coinSet.forEach(coinObj=>{
        const {id,symbol,name,image,current_price,market_cap,market_cap_rank,total_volume,high_24h,low_24h,price_change_24h,price_change_percentage_24h}=coinObj;
        console.log(id, current_price)
        let trclick= document.createElement("tr")
        tbody.appendChild(trclick)
        trclick.innerHTML+=`
       
                    
                   
                    <td>${name}</td>
                    <td>${symbol}</td>
                    <td>${current_price}</td>
                    
                    <td>${high_24h}</td>
                    <td>${low_24h}</td>
                    <td>${price_change_percentage_24h}</td>
                    
                
        `
        trclick.style.cursor = "pointer"
        trclick.onclick=()=>{
            deleteCoinFromWatchlist(id)
        }
    })

}
//COINS
const getAllCoins = async()=>{
   
    let response = await fetch(baseUrl + "/coins/allCoins",{
        method:"GET",
        headers:headers
    }).catch(err=>console.error(err))

   let responseArr= await response.json();
//    console.log(responseArr)
   
   coinsTable.innerHTML=``
   let row = 1;
 responseArr.forEach(coin=>{
    // console.log(coin.id)
  
  let trclick= document.createElement("tr")
  coinsTable.appendChild(trclick)
    trclick.innerHTML += `
   
    <th scope="row">${row}</th>
    <td >${coin.name}</td>
    <td>${coin.symbol}</td>
    <td>${coin.current_price}</td>
    <td>${coin.high_24h}</td>
    <td>${coin.low_24h}</td>
    <td>${coin.price_change_percentage_24h}</td>
    
 
    `
    // let watchlist = footer.getElementsByTagName('*');
    // let result=''
    // let number=0
    trclick.onclick=()=>{
        console.log(coin.id)
addCoinToWatchlist(coin.id)
    }
    // for(let i=0; i<watchlist.length; i++){
    //     if(watchlist[i].id===`${coin.id}`){
    //         result=watchlist[i];
    //         number=i
            
    //         console.log(watchlist[number])
    //         console.log(number)
    //         result.onclick = ()=>addCoinToWatchlist(coin.id)
    //         break;
    //     }
    // }
    
    // addCoinToWatchlist(coin.id)
    // console.log(watchlist[number])



// coinRow.onclick= addCoinToWatchlist(coin.id)
    row++;
   })


}
const addCoinToWatchlist = async (coin)=>{
    console.log(coin)
   let  response = await fetch(baseUrl + `/watchlists/${cookieObj.watchlistId}/coin/${coin}`,{
    method:"PUT",
    headers:headers
   }).catch(err=>console.error(err))
   console.log(coin.innerHTML + " added to watchlist " +cookieObj.watchlistId)
   getWatchlistById(cookieObj.watchlistId)
}
const deleteCoinFromWatchlist = async (coin)=>{
    console.log(coin)
    let  response = await fetch(`http://localhost:8080/watchlists/${cookieObj.watchlistId}/coin/${coin}`,{
        method:"DELETE",
        headers:headers
       }).catch(err=>console.error(err))
       console.log(coin.innerHTML + " deleted from watchlist " +cookieObj.watchlistId)
       getWatchlistById(cookieObj.watchlistId)
}
body.onload = getAllCoins()
getAllCoinsBtn.addEventListener("click",getAllCoins())
body.onload=getWatchlistById(cookieObj.watchlistId)