const username = document.getElementById("username")
const password = document.getElementById("password")
const loginForm = document.getElementById("loginForm")
const wholepage = document.getElementById("wholepage")
const body=document.getElementById("body")

const baseUrl = "http://localhost:8080"
const headers= {
    'Content-Type':'application/json',
    'Access-Control-Allow-Origin':'*'
}
const delay = ms=>new Promise(res => setTimeout(res,ms));

const loginSubmit = async (e)=>{
    e.preventDefault()
    
const alert = document.getElementById("alert")
// body.innerHTML+=`
// <div id="wholepage" class="fa-4x visible" style="padding:48vw; color:azure">
//         <i class="fa-solid fa-spinner fa-spin-pulse" ></i>
//     </div>
// `
    let loginObj = {
        name: username.value,
        password: password.value
    }
    console.log(loginObj.name,loginObj.password)
    const response = await fetch(`${baseUrl}/users/login`,{
        method: "POST",
        body: JSON.stringify(loginObj),
        headers:headers})
    .catch(err=> console.error(err.message))
   
    // wholepage.classList.add("visible")

    responseArr = await response.json()

    console.log(responseArr[0])
    if(responseArr[0]!="Username or password incorrect"){
        console.log(responseArr[1],responseArr[2])
        // await delay(10000)
        document.cookie=`userName=${responseArr[1]}`
        document.cookie=`userId=${responseArr[2]}`
        
        window.location.replace("http://localhost:8080/home.html").catch(err=> console.error(err.message))


        
    }else{
        console.log(responseArr[0])
            alert.classList.add("alert-active")
            await delay(3000);
            alert.classList.remove("alert-active")
    }
}
const reuploadCoins = async()=>{
    let response = await fetch(`http://localhost:8080/addCoins`,{
        method:"GET",
        headers:headers
    }).catch(err=>console.error(err))
    console.log("reuploading coins")
}
loginForm.addEventListener("submit",loginSubmit)
window.addEventListener("load",reuploadCoins)







