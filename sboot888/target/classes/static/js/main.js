async function loadIntoTable (url, table){
    const tableHead = table.querySelector('thead');
    const tableBody = table.querySelector('tbody');
    const response = await fetch(url);
    const { headers, rows} = await response.json();

    tableHead.innerHTML = '<tr></tr>';
    tableBody.innerHTML = '';

    for(const headerText of headers) {
        const headerElement = document.createElement('th');
        headerElement.textContent = headerText;
        tableHead.querySelector('tr').appendChild(headerElement);
    }
}

loadIntoTable('http://localhost:8080/admin/users', document.querySelector('table'));
