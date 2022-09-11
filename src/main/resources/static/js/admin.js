const mainTable = document.querySelector('#main-body')
const addUserForm = document.querySelector('#addNewUser')
const nameValue = document.querySelector('#name-value')
const surnameValue = document.querySelector('#username-value')
const ageValue = document.querySelector('#age-value')
const usernameValue = document.querySelector('#username-value')
const passwordValue = document.querySelector('#password-value')
const rolesValue = document.querySelector('#roles-value')
const navTabHome = document.querySelector('#nav-home-tab')
let result = ''
const myModal = new bootstrap.Modal(document.getElementById('windowModal'))
const myForm = document.getElementById('myForm')
const modalName = document.getElementById('modalName')
const modalSurname = document.getElementById('modalSurname')
const modalAge = document.getElementById('modalAge')
const modalUsername = document.getElementById('modalUsername')
const modalPassword = document.getElementById('modalPassword')
const modalRole = document.getElementById('modalRole')


// Я НЕ ХОЧУ БОЛЬШЕ ДЕЛАТЬ ЭТОТ JS!!!! Я чуть с ума не сошел.... Прими пожалуйста! Времени очень мало у меня осталось ((((

const showUsers = (users, deleted = false) => {


    if (deleted) {
        mainTable.innerHTML = ''
        result = ''
    }

    users.forEach(user => {
        result += `<tr>
                <td>${user.id}</td>
                <td>${user.name}</td>
                <td>${user.surname}</td>
                <td>${user.age}</td>
                <td>${user.username}</td>
                <td>${user.password}</td>
                <td>${user.roles.map(x => x.name.slice(5))}</td>    
                <td>
                    <button type="submit" class="btn btn-info" data-bs-toggle="modal" id = "edit-user">EDIT
                    </button>
                </td>
                <td data-id=${user.id}>                    
                        <button type="submit" class="btn btn-danger" id = "delete-user">DELETE</button>                    
                </td>          
                </tr>`
    })
    mainTable.innerHTML = result
}


fetch('http://localhost:3030/api').then(res => res.json()).then(data => {
    showUsers(data)
})



addUserForm.addEventListener('submit', (e) => {
    e.preventDefault();


    fetch('http://localhost:3030/api/createUser', {
        method: 'POST',
        headers: {
            'Content-Type': 'application/json;charset=utf-8'
        },
        body: JSON.stringify({
            name: nameValue.value,
            surname: surnameValue.value,
            age: ageValue.value,
            username: usernameValue.value,
            password: passwordValue.value,
            roles: roleUser(rolesValue.value)

        })
    })

        .then(res => res.json())
        .then(data => {
            navTabHome.click()
            const dataArr = [];
            dataArr.push(data);
            showUsers(dataArr);
        })
    alert('User add')
})

const roleUser = role => {
    const rolesArr = {};
    if (role == 1) {
        rolesArr.id = 1
        rolesArr.name = 'ROLE_ADMIN'
    } else {
        rolesArr.id = 2
        rolesArr.name = 'ROLE_USER'
    }

    return rolesArr
}


const on = (element, event, selector, handler) => {
    element.addEventListener(event, e => {
        if (e.target.closest(selector)) {
            handler(e)
        }
    })
}

on(document, 'click', '#delete-user', e => {
    const id = e.target.parentNode.parentNode.firstElementChild.innerHTML

    alertify.confirm("Delete user?",
        function () {
            fetch(`http://localhost:3030/api/user/${id}`, {
                method: 'DELETE'
            }).then(res => res.json())
                .then(data => {
                    showUsers(data, true);
                })
        },
        function () {
            alertify.error('Cancel');
        });
})


on(document, 'click', '#edit-user', e => {
    const form = e.target.parentNode.parentNode
    const nameForm = form.children[1].innerHTML
    const surnameForm = form.children[2].innerHTML
    const ageForm = form.children[3].innerHTML
    const usernameForm = form.children[4].innerHTML
    const passwordForm = form.children[5].innerHTML
    const tempId = e.target.parentNode.parentNode.firstElementChild.innerHTML
    modalName.dataset.id = tempId
    myModal.show()
    modalName.value = nameForm
    modalSurname.value = surnameForm
    modalAge.value = ageForm
    modalUsername.value = usernameForm
    modalPassword.value = passwordForm
})

myForm.addEventListener('submit', (e) => {
    e.preventDefault()
    const id = modalName.dataset.id
    fetch(`http://localhost:3030/api/${id}`, {
        method: 'PATCH',
        headers: {
            'Content-Type': 'application/json;charset=utf-8'
        },
        body: JSON.stringify({
            name: modalName.value,
            surname: modalSurname.value,
            age: modalAge.value,
            username: modalUsername.value,
            password: modalPassword.value,
            roles: roleUser(modalRole.value)

        })
    }).then(res => res.json())
        .then(data => {
            showUsers(data, true);
        })

    myModal.hide()
})