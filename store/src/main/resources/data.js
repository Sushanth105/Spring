
async function fetching(params) {
    let response = await fetch("http://localhost:8080/datas",{
        method : "POST"
    });
    let data = await response.json();
    console.log(data);
}

async function sending(params) {
    await fetch("http://localhost:8080/send",{
        method : "POST",
        headers : {"Content-Type" : "application/json"},
        body : JSON.stringify([{name : "sushanth",age : 19}])
    });
}

// fetching();
sending();