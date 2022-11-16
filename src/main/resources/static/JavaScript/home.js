const greeting = document.getElementById("greeting")
const cookieObj = document.cookie.split(`;`).map(cookie=> cookie.split('=')).reduce((accumulator,[key,value])=>
({...accumulator,[key.trim()]: decodeURIComponent(value)}),
{});
console.log(cookieObj)


const baseUrl = "http://localhost:8080"
const headers= {
    'Content-Type':'application/json',
    'Access-Control-Allow-Origin':'*'
}

const delay = ms=>new Promise(res => setTimeout(res,ms));

const main = document.getElementById("main")
const addWatchlistBtn = document.getElementById("addWatchlist")
const getWatchlistBtn = document.getElementById("getWatchlist")
const getAllCoinsBtn=document.getElementById("getAllCoins")
const addCoinsBtn=document.getElementById("addCoin-btn")
const coinsTable=document.getElementById("coins-table")

greeting.innerHTML = `Hello ${cookieObj.userName}`

// console.log(userIdString[1])




const addWatchlist = async (e)=>{
    e.preventDefault()
    console.log("add Watchlist")
 

let response = await fetch(`http://localhost:8080/users/${cookieObj.userId}/addWatchlist`,{
    method: "POST",
    headers:headers
}).catch(err=>console.error(err))
   main.innerHTML=``
let responesArr= await response.json();
if(response.status==200){
    getAllWatchlists()

}else{
    console.log("response error")
}
}
const deleteWatchlist= async (id)=>{
    let response = await fetch(`http://localhost:8080/watchlists/${id}`,{
        method:"DELETE",
        headers:headers
    }).catch(err=>console.error(err))
    console.log(id)
    // let responesArr= await response.json();
    // console.log(responesArr)
    console.log(response)
    getAllWatchlists()
}
const getAllWatchlists = async(e)=>{
    // e.preventDefault()
    let response = await fetch(`http://localhost:8080/watchlists/users/${cookieObj.userId}`,{
        method:"GET",
        headers:headers
    })
    let responesArr= await response.json();
    console.log(responesArr)
    if(response.status==200){
        main.innerHTML =``;
       let watchlistNumber=0
        responesArr.forEach(obj => {
            watchlistNumber++
            
            main.innerHTML+=`   
            <div class="watchlist-container" >
            <div class="watchlist-header">${watchlistNumber}</div>
            <div class="watchlist-body table-responsive">
              <table class="table table-striped">
                <thead>
                  <tr >
                    
                    <th scope="col">Name</th>
                    <th scope="col">Symbol</th>
                    <th scope="col">Current Price</th>
                    <th scope="col">24h Hi</th>
                    <th scope="col">24h Lo</th>
                    <th scope="col">%</th>
                    <th scope="col"></th>
                  </tr>
                </thead>
                <tbody id="${obj.id}">
                 
                </tbody>
              </table>
               </div>
                <button onClick="deleteWatchlist(${obj.id})" class="delete" id="delete-btn">delete</button>
                <button  class="delete" onClick="addCoinsToWatchlist(${obj.id},${watchlistNumber})" id="addCoin-btn">Edit Coins</button>
            </div>`
           
            let watchlist = main.getElementsByTagName('*');
            let result=''
            let number=0
            
            for(let i=0; i<watchlist.length; i++){
                if(watchlist[i].id===`${obj.id}`){
                    result=watchlist[i];
                    number=i
                    break;
                }
            }
            // console.log(result)
            // console.log(number)
            obj.coinSet.forEach(coin=>{
                const {id,symbol,name,image,current_price,market_cap,market_cap_rank,total_volume,high_24h,low_24h,price_change_24h,price_change_percentage_24h}=coin;
                const watchlist=main.getElementsByTagName("*")
                const tbody=watchlist[number]
                console.log(tbody)
                tbody.innerHTML += `
                <tr>
               
                    <td>${name}</td>
                    <td>${symbol}</td>
                    <td>${current_price}</td>
                    
                    <td>${high_24h}</td>
                    <td>${low_24h}</td>
                    <td>${price_change_percentage_24h}</td>
                    
                </tr>
                `
            })
        })
    }
}



// COINS
const getAllCoins = async(e)=>{
    e.preventDefault()
    let response = await fetch(baseUrl + "/coins/allCoins",{
        method:"GET",
        headers:headers
    }).catch(err=>console.error(err))

   let responseArr= await response.json();
   console.log(responseArr)
   
   coinsTable.innerHTML=``
   let row = 1;
 responseArr.forEach(coin=>{
    coinsTable.innerHTML += `
    <tr>
    <th scope="row">${row}</th>
    <td>${coin.name}</td>
    <td>${coin.symbol}</td>
    <td>${coin.current_price}</td>
    <td>${coin.high_24h}</td>
    <td>${coin.low_24h}</td>
    <td>${coin.price_change_percentage_24h}</td>
  </tr>
    `
    row++;
   })


}
const addCoinsToWatchlist = (watchlist,watchlistNumber)=>{


    console.log("addCoins button for: "+ watchlistNumber)
    document.cookie=`watchlistId=${watchlist}`;
    // document.cookie=`watchlistNumber=1`;
    // document.cookie=;
    window.location.replace(`http://localhost:8080/addCoin.html`)
    
}
const reuploadCoins = async()=>{
    let response = await fetch(`http://localhost:8080/addCoins`,{
        method:"GET",
        headers:headers
    }).catch(err=>console.error(err))
    console.log("reuploading coins")
}
addWatchlistBtn.addEventListener("click",addWatchlist)
document.addEventListener("DOMContentLoaded",getAllCoins)
window.addEventListener("load",getAllWatchlists) 
window.addEventListener("load",reuploadCoins())
// getAllCoinsBtn.addEventListener("click",getAllCoins)



