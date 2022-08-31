const editForm = document.getElementById("edit");
const deleteFrom = document.getElementById("delete");
editForm.addEventListener("submit", async (e) => {

    e.preventDefault();
    let form = e.currentTarget;
    let url = form.action;
    try {
        let formData = new FormData(form);
        console.log(form)
        let responseData = await postFormFieldsAsJson({url, formData});
        let {serverDataResponse} = responseData;
        console.log(serverDataResponse);
        window.location.replace("http://localhost:8080/list");
    } catch (error) {
        console.error(error);
    }
});


async function postFormFieldsAsJson({url, formData}) {
    let formDatanew = {
        "title": formData.get("title"),
        "description": formData.get("description"),
        "model": formData.get("model"),
        "type": formData.get("type"),
        "unit": formData.get("unit"),
        "location": formData.get("location"),
        "id": formData.get("id"),
        "range": {
            "to": formData.get("range.to"),
            "from": formData.get("range.from")
        }
    }
    let formDataJsonString = JSON.stringify(formDatanew);

    let fetchOptions = {

        method: formData.get("_method"),

        headers: {
            "Content-Type": "application/json",
            Accept: "application/json",
        },

        body: formDataJsonString,
    };

    let res = await fetch(url, fetchOptions);


    if (!res.ok) {
        let error = await res.text();
        throw new Error(error);
    }

    return res.json();
}

deleteFrom.addEventListener("submit", async (e) => {
    e.preventDefault();
    let form = e.currentTarget;
    let url = form.action

    try {
        let formData = new FormData(form)

        let responseData = await postDeleteForm(url, formData);

        let {serverDataResponse} = responseData;


        console.log(serverDataResponse);
        window.location.replace("http://localhost:8080/list");
    } catch (error) {

        console.error(error);
    }
});

async function postDeleteForm(url, formData) {


    let fetchOptions = {

        method: formData.get("_method"),

        headers: {
            "Content-Type": "application/json",
            Accept: "application/json",
        },
        body: {},


    };
    let resp = await fetch(url, fetchOptions);
    if (!resp.ok) {
        let error = await resp.text();
        throw new Error(error);
    }
    return resp.ok;
}
