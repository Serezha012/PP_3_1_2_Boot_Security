let navbarName = document.querySelector('#navbar-name')
let tmp = ''

const userInfo = fetch('api/getUser')
    .then(res => res.json())


userInfo.then(user => {
    tmp += `${user.username} with rolesss ${user.roles.map(x => x.name.slice(5))}`
    navbarName.innerHTML = tmp
})

let tableInfo = document.querySelector('#table-info')
let tmp2 = ''

userInfo.then(user => {
    tmp2 += ` <tr>
                                <td>${user.id}</td>
                                <td>${user.name}</td>
                                <td>${user.surname}</td>
                                <td>${user.age}</td>
                                <td>${user.username}</td>
                                <td>${user.password}</td>
                                <td>${user.roles.map(x => x.name.slice(5))}</td>
                            </tr>`

    tableInfo.innerHTML = tmp2
})




