const registerForm = document.getElementById("registerForm")
const username = document.getElementById("username")
const password = document.getElementById("password")
const body = document.getElementById("body")
const back= document.getElementById("arrow")

const baseUrl = "http://localhost:8080"
const headers= {
    'Content-Type':'application/json',
    'Access-Control-Allow-Origin':'*'
}
const delay = ms=>new Promise(res => setTimeout(res,ms));

// body.innerHTML = `<h3>User Registered<h3>`;
const handleRegister =async e=>{
    e.preventDefault()
    // registerForm.innerHTML=`<p style="color:white;">connected</p>`
    let registerObject={
        name:username.value,
        password:password.value
    }

    let response = await fetch(`${baseUrl}/users/addUser`,{
        method:"POST",
        body:JSON.stringify(registerObject),
        headers:headers
    }).catch(err=>console.error(err.message))
    console.log(response.body.value)
    const responseArr = await response.json();

    if(response.status===200){
        
       await delay(1000);
        window.location.replace("http://localhost:8080/login.html");
    }else{
        console.log("error")
    }


}
const loginPage=()=>{
    window.location.replace("http://localhost:8080/login.html");
}
back.addEventListener("click",loginPage);
registerForm.addEventListener("submit",handleRegister);
