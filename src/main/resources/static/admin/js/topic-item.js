function setItems(specific_tbody, key, id, name, regularPrice, salePrice, stock) {
    let row = specific_tbody.insertRow(key);
    var cell1 = row.insertCell(0);
    var cell2 = row.insertCell(1);
    var cell3 = row.insertCell(2);
    var cell4 = row.insertCell(3);
    var cell5 = row.insertCell(4);
    var cell6 = row.insertCell(5);
    var cell7 = row.insertCell(6);

    cell1.innerHTML = `
        <td class="td_chk">
            <label for="item_check_${key}" class="sound_only"></label>
            <input type="checkbox" name="itemChecks[]" id="item_check_all${key}"/>
        </td>
        `;

    cell2.innerHTML = `
        <td class="opt-cell">
            <label for="item_id_${key}" class="sound_only"></label>
            <input type="hidden" name="itemIds[]" id="item_id_${key}" class="frm_input" value="${id}"/>
            <span>${id}</span>
        </td>
        `;

    cell3.innerHTML = `
        <td class="opt-cell">
            <label for="item_name_${key}" class="sound_only"></label>
            <input type="hidden" name="itemNames[]" id="item_name_${key}" class="frm_input" value="${name}"/>
            <span>${name}</span>
        </td>
        `;

    cell4.innerHTML = `
        <td class="td_numsmall">
            <label for="item_regular_price_${key}" class="sound_only"></label>
            <input type="hidden" name="itemRegularPrices[]" id="item_regular_price_${key}" class="frm_input" value="${regularPrice}"/>
            <span>${regularPrice}</span>
        </td>
        `;

    cell5.innerHTML = `
        <td class="td_num">
            <label for="item_sale_price_${key}" class="sound_only"></label>
            <input type="hidden" name="itemSalePrices[]" id="item_sale_price_${key}" class="frm_input" value="${salePrice}"/>
            <span>${salePrice}</span>
        </td>
        `;

    cell6.innerHTML = `
        <td class="td_num">
            <label for="item_stock_${key}" class="sound_only"></label>
            <input type="hidden" name="itemStocks[]" id="item_stock_${key}" class="frm_input" value="${stock}"/>
            <span>${stock}</span>
        </td>
        `;

    cell7.innerHTML = `
        <td class="td_num">
            <button class="btn btn_basic" type="button" onclick="removeItem(${key})">삭제</button>
        </td>
        `;
}

function itemsSearch() {
    const params = {
        search: document.getElementById("items_search_input").value
    }

    let query = Object.keys(params)
        .map(k => encodeURIComponent(k) + '=' + encodeURIComponent(params[k]))
        .join('&');

    fetch('/api/admin/items?' + query)
        .then(data => data.text())
        .then(text => {
            const response = JSON.parse(text);
            let itemPage = response.itemPage;
            const items = itemPage.content;
            setItems(items);
        })
        .catch(function (errors) {
            console.log(errors)
        });

    function setItems(items) {
        const table = document.getElementById('item_table_body_2');

        const parent = document.querySelector("#item_table_body_2");

        while (parent.firstChild) {
            parent.removeChild(parent.firstChild);
        }

        for (let i = 0; i < items.length; i++) {
            const item = items[i];
            let row = table.insertRow(i);
            var cell1 = row.insertCell(0);
            var cell2 = row.insertCell(1);
            var cell3 = row.insertCell(2);
            var cell4 = row.insertCell(3);
            var cell5 = row.insertCell(4);
            var cell6 = row.insertCell(5);
            var cell7 = row.insertCell(6);

            cell1.innerHTML = `
            <td className="td_chk">
                <input type="checkbox" name="itemChecks2[]" value="0" id="item_${item.id}">
            </td>`;
            cell2.innerHTML = `
            <td className="td_numbig">
                <span value="${item.id}" name="itemIds2[]">${item.id}</span>
            </td>`;
            cell3.innerHTML = `
            <td className="td_num">
<!--                <img src="#" width="50" height="50" alt="">-->
            </td>`;
            cell4.innerHTML = `
            <td headers="th_pc_title" className="td_input td_name">
                <p value="${item.name}" name="itemNames2[]">${item.name}</p>
            </td>`;
            cell5.innerHTML = `
            <td headers="th_amt" className="td_numbig td_input">
                <span value="${item.regularPrice}" name="itemRegularPrices2[]">${item.regularPrice}</span>
            </td>`;
            cell6.innerHTML = `
            <td headers="th_amt2" className="td_numbig td_input">
                <span value="${item.salePrice}" name="itemSalePrices2[]">${item.salePrice}</span>
            </td>`;
            cell7.innerHTML = `
            <td headers="th_qty" className="td_numbig td_input">
                <span value="${item.stock}" name="itemStocks2[]">${item.stock}</span>
            </td>`;
        }
    }
}

function addCheckItems() {
    let item_table_body = document.getElementById("item_table_body");
    let length = item_table_body.rows.length;

    const itemIds = document.getElementsByName("itemIds[]");
    let ids = Object.entries(itemIds)
        .map(([index, elem], key) => {
            return elem.value;
        });

    const itemChecks2 = document.getElementsByName("itemChecks2[]");
    const itemIds2 = document.getElementsByName("itemIds2[]");
    const itemNames2 = document.getElementsByName("itemNames2[]");
    const itemRegularPrices2 = document.getElementsByName("itemRegularPrices2[]");
    const itemSalePrices2 = document.getElementsByName("itemSalePrices2[]");
    const itemStocks2 = document.getElementsByName("itemStocks2[]");
    Object.entries(itemChecks2)
        .map(([index, elem], key) => {
            if (elem.checked) {
                if (!ids.includes(itemIds2[key].innerHTML)) {
                    setItems(item_table_body, length, itemIds2[key].innerHTML, itemNames2[key].innerHTML, itemRegularPrices2[key].innerHTML, itemSalePrices2[key].innerHTML, itemStocks2[key].innerHTML);
                    length++;
                }
            }
        });
}

const allCheckItem = () => {
    const allCheckbox = document.getElementById("item_check_all").checked;
    const checkList = document.getElementsByName("itemChecks[]");

    Object.entries(checkList).map(([index, elem], key) => {
        checkList[key].checked = allCheckbox;
    });
};

const allCheckItem2 = () => {
    const allCheckbox = document.getElementById("item_check_all_2").checked;
    const checkList = document.getElementsByName("itemChecks2[]");

    Object.entries(checkList).map(([index, elem], key) => {
        checkList[key].checked = allCheckbox;
    });
};

const removeItem = (thisKey) => {
    const parent = document.querySelector("#item_table_body");

    Object.entries(parent.childNodes).map(([index, elem], key) => {
        if (thisKey === parseInt(index)) {
            elem.remove();
        }
    });
}

const removeCheckItem = () => {
    const parent = document.querySelector("#item_table_body");

    const checkList = document.getElementsByName("itemChecks[]");
    let checkedItemIds = Object.entries(checkList)
        .map(([index, elem], key) => {
            if (elem.checked) {
                return index;
            }
        });

    Object.entries(parent.childNodes).map(([index, elem], key) => {
        if (checkedItemIds.includes(index)) {
            elem.remove();
        }
    });
    document.getElementById("item_check_all").checked = false;
}
