//   variable

// 1. 메인분류 노출
let ca_checkbox = "";
let bestBadge = "";
let newBadge = "";
let ca_category = "";

// 2. 기본정보
let basic_code = ""; // id
let itemName = "";
let englishName = "";
let basicDescription = "";
let manufacturer = "";
let origin = "";
let brand = "";
let barcode = "";
let modelName = "";
let keyword = "";

// 3. 판매정보
let status = "";
let regularPrice = null;
let sales_product_taxation_type = "";
let salePrice = null;
let fees = null;
let sales_point_type = "";
let point = null;
let stock = null;
let stockNotificationQuantity = "";
let minimumPurchaseQuantity = "";
let maximumPurchaseQuantity = "";
let sales_option = [
  {
    option: "",
    item: "",
  },
];
let sales_selection_option = [
  {
    check: false,
    option: "",
    add_price: null,
    available_stock: null,
    sales_notified_available_stock: null,
    usage_options: "",
  },
];
let sales_product_additional_options = [
  {
    additional: "",
    item: "",
  },
];

// 일괄 변경 옵션으로 병량님은 사용하지 않으셔도 됩니다.
let sales_every_option_additional_price = null;
let sales_every_option_available_stock = "";
let sales_every_option_notified_available_stock = "";
let sales_every_option_use = "";

// 4. 상품정보
let product_product_classification = "";
let productionYear = null;
let manufacturingYear = "";
let manufacturingMonth = "";
let seasonYear = "";
let season = "";
let product_color = "";

// 5. 이미지
let img_1 = undefined;
let img_2 = undefined;
let img_3 = undefined;
let img_4 = undefined;
let img_5 = undefined;
let img_6 = undefined;
let img_7 = undefined;
let img_8 = undefined;
let img_9 = undefined;
let img_10 = undefined;

//6. 상세설명
let detail_description = "";
let detail_top_content = "";
let detail_bottom_content = "";

//7. 상품 정보 고시
let proinfo_product = "";
let productName = "";
let foodType = "";
let producerAndLocation = "";
let qualityMaintenancePeriod = "";
let quantityPerUnit = "";
let rawMaterials = "";
let nutrient = "";
let geneticallyModifiedFood = "";
let safetyPrecautions = "";
let importedFoodStationery = "";
let managerAndPhoneNumber = "";
let proinfo_kinds = "";
let proinfo_weight = "";
let proinfo_length = "";

//8. 기타정보
let etc_number = "";
let etc_certification_date = "";
let etc_start_date = "";
let etc_end_date = "";
let etc_Issued_date = "";
let etc_certification = "";
let etc_certification_field = "";
let etc_income_number = "";
let etc_income_img = "";
let etc_email = "";
let etc_admin_memo = "";

// funcition

// const handleChnageInput = () => {
//   console.log(document.getElementsByName("#it_name"));

//   //   console.log("value", document.querySelector(id));
//   //   variable = value;
// };

// 1. 메인분류 노출
const mainCategoryCheckbox = (event) => {
  ca_checkbox = event.target.value;
  // if (event.target.value === "best") {
  //   document.querySelector("#bestBadge").checked = false;
  // } else {
  //   document.querySelector("#newBadge").checked = false;
  // }

  console.log(ca_checkbox);
};

//2. 기본정보

//3. 판매정보

// 상품상태
const productStatus = (id) => {
  //   console.log(id);
  status = event.target.value;

  // if (event.target.id == "beforeApprovalStatus") {
  //   event.target.checked = true;
  //   document.querySelector("#saleStatus").checked = false;
  //   document.querySelector("#productExposureStatus").checked = false;
  //   document.querySelector("#soldOutStatus").checked = false;
  //   document.querySelector("#noSaleStatus").checked = false;
  // } else if (event.target.id == "saleStatus") {
  //   event.target.checked = true;
  //   document.querySelector("#beforeApprovalStatus").checked = false;
  //   document.querySelector("#productExposureStatus").checked = false;
  //   document.querySelector("#soldOutStatus").checked = false;
  //   document.querySelector("#noSaleStatus").checked = false;
  // } else if (event.target.id == "productExposureStatus") {
  //   event.target.checked = true;
  //   document.querySelector("#beforeApprovalStatus").checked = false;
  //   document.querySelector("#saleStatus").checked = false;
  //   document.querySelector("#soldOutStatus").checked = false;
  //   document.querySelector("#noSaleStatus").checked = false;
  // } else if (event.target.id == "soldOutStatus") {
  //   event.target.checked = true;
  //   document.querySelector("#beforeApprovalStatus").checked = false;
  //   document.querySelector("#saleStatus").checked = false;
  //   document.querySelector("#productExposureStatus").checked = false;
  //   document.querySelector("#noSaleStatus").checked = false;
  // } else if (event.target.id == "noSaleStatus") {
  //   event.target.checked = true;
  //   document.querySelector("#beforeApprovalStatus").checked = false;
  //   document.querySelector("#saleStatus").checked = false;
  //   document.querySelector("#soldOutStatus").checked = false;
  //   document.querySelector("#productExposureStatus").checked = false;
  // }

  console.log(`status`, status);
};

// 과세 비과세

const taxationType = () => {
  sales_product_taxation_type = event.target.value;

  console.log("sales_product_taxation_type", sales_product_taxation_type);
};

// 상품정보

const itemSortation = () => {
  product_product_classification = event.target.value;

  console.log("product_product_classification", product_product_classification);
};

//상품정보 제조일 년

// let year;
// let month;

// const selectYear = () => {
//   year = event.target.value;
//   console.log("product_made_date", year);
// };

// const selectMonth = () => {
//   month = event.target.value;
//   console.log("month", month);
//   productManufacture();
// };

// function productManufacture(selectYear, selectMonth) {
//   product_manufacture = `제조일: ${year}년 ${month}월`;
//   console.log(product_manufacture);
// }

//상품선택옵션 옵션추가
const addOptinList = () => {
  sales_option.push({
    option: "",
    item: "",
  });

  console.log(sales_option);

  let specific_tbody = document.getElementById("option_table");
  let row = specific_tbody.insertRow(specific_tbody.rows.length);
  var cell1 = row.insertCell(0);

  var cell2 = row.insertCell(1);

  sales_option.map((option, key) => {
    cell1.innerHTML = ` <label for="opt${key + 1}_subject">옵션${
      key + 1
    }</label>
    <input
        type="text"
        name={opt${key + 1}_subject}
        id={opt${key + 1}_subject}
        class="frm_input"
        size="15"
    
        />
        `;

    cell2.innerHTML = `<label for="opt${key + 1}"><b>옵션${
      key + 1
    } 항목</b></label>
      <input
        type="text"
        name={opt${key + 1}}
        id={opt${key + 1}}
        class="frm_input"
        size="50"
    
      />
      <button onclick=deleteOption(${key}) >
      삭제
      </button>
      `;
  });
};

// onchange="
// `(function () {
// sales_option[key].item = event.target.value;
// //   console.log(`basic_brand`, sales_option);
// })()`"

const deleteOption = (key) => {
  let specific_tbody = document.getElementById("option_table");

  sales_option.splice(key + 1, 1);
  specific_tbody.children.length === 2
    ? specific_tbody.children[key].remove()
    : specific_tbody.children[key + 1].remove();
};
{
  /* <tbody>
  <tr>
    <td class="td_chk">
      <input type="hidden" name="opt_id[]" value="WHITE1" />
      <label for="opt_chk_0" class="sound_only"></label>
      <input type="checkbox" name="opt_chk[]" id="opt_chk_0" value="1" />
    </td>
    <td class="opt-cell">
      WHITE <small>&gt;</small> 1
    </td>
    <td class="td_numsmall">
      <label for="opt_price_0" class="sound_only"></label>
      <input
        type="text"
        name="opt_price[]"
        value="0"
        id="opt_price_0"
        class="frm_input"
        size="9"
      />
    </td>
    <td class="td_num">
      <label for="opt_stock_qty_0" class="sound_only"></label>
      <input
        type="text"
        name="opt_stock_qty[]"
        value="20"
        id="opt_stock_qty_0"
        class="frm_input"
        size="5"
      />
    </td>
    <td class="td_num">
      <label for="opt_noti_qty_0" class="sound_only"></label>
      <input
        type="text"
        name="opt_noti_qty[]"
        value="100"
        id="opt_noti_qty_0"
        class="frm_input"
        size="5"
      />
    </td>
    <td class="td_mng">
      <label for="opt_use_0" class="sound_only"></label>
      <select name="opt_use[]" id="opt_use_0">
        <option value="1" selected="selected">
          사용함
        </option>
        <option value="0">사용안함</option>
      </select>
    </td>
  </tr>
  <tr>
    <td class="td_chk">
      <input type="hidden" name="opt_id[]" value="WHITE2" />
      <label for="opt_chk_1" class="sound_only"></label>
      <input type="checkbox" name="opt_chk[]" id="opt_chk_1" value="1" />
    </td>
    <td class="opt-cell">
      WHITE <small>&gt;</small> 2
    </td>
    <td class="td_numsmall">
      <label for="opt_price_1" class="sound_only"></label>
      <input
        type="text"
        name="opt_price[]"
        value="0"
        id="opt_price_1"
        class="frm_input"
        size="9"
      />
    </td>
    <td class="td_num">
      <label for="opt_stock_qty_1" class="sound_only"></label>
      <input
        type="text"
        name="opt_stock_qty[]"
        value="20"
        id="opt_stock_qty_1"
        class="frm_input"
        size="5"
      />
    </td>
    <td class="td_num">
      <label for="opt_noti_qty_1" class="sound_only"></label>
      <input
        type="text"
        name="opt_noti_qty[]"
        value="100"
        id="opt_noti_qty_1"
        class="frm_input"
        size="5"
      />
    </td>
    <td class="td_mng">
      <label for="opt_use_1" class="sound_only"></label>
      <select name="opt_use[]" id="opt_use_1">
        <option value="1" selected="selected">
          사용함
        </option>
        <option value="0">사용안함</option>
      </select>
    </td>
  </tr>
</tbody>; */
}

{
  /* <td class="td_mng">
      <label for="opt_use_1" class="sound_only"></label>
      <select name="opt_use[]" id="opt_use_1">
        <option value="1" selected="selected">
          사용함
        </option>
        <option value="0">사용안함</option>
      </select>
    </td> */
}

const createOptionList = () => {
  sales_product_additional_options = sales_option;
  console.log(sales_product_additional_options);

  let specific_tbody = document.getElementById("product_option_table");
  let row = specific_tbody.insertRow(specific_tbody.rows.length);
  var cell1 = row.insertCell(0);
  var cell2 = row.insertCell(1);
  var cell3 = row.insertCell(2);
  var cell4 = row.insertCell(3);
  var cell5 = row.insertCell(4);
  var cell6 = row.insertCell(5);

  sales_selection_option.map((option, key) => {
    cell1.innerHTML = `
    <td class="td_chk">
    <input type="hidden" name="opt_id[]" value="WHITE1" />
    <label for="opt_chk_0" class="sound_only"></label>
    <input type="checkbox" name="opt_chk[]" id="opt_chk_0" value="1" />
  </td>
    `;

    cell2.innerHTML = `
    <td class="opt-cell">
    WHITE <small>&gt;</small> 1
  </td>
    `;

    cell3.innerHTML = `
    <td class="td_numsmall">
    <label for="opt_price_0" class="sound_only"></label>
    <input
      type="text"
      name="opt_price[]"
      value="0"
      id="opt_price_0"
      class="frm_input"
      size="9"
    />
  </td>
    `;

    cell4.innerHTML = `
    <td class="td_num">
      <label for="opt_stock_qty_0" class="sound_only"></label>
      <input
        type="text"
        name="opt_stock_qty[]"
        value="20"
        id="opt_stock_qty_0"
        class="frm_input"
        size="5"
      />
    `;

    cell5.innerHTML = `
    <td class="td_num">
    <label for="opt_noti_qty_0" class="sound_only"></label>
    <input
      type="text"
      name="opt_noti_qty[]"
      value="100"
      id="opt_noti_qty_0"
      class="frm_input"
      size="5"
    />
  </td>
  
    `;

    cell6.innerHTML = `
    <td class="td_mng">
    <label for="opt_use_0" class="sound_only"></label>
    <select name="opt_use[]" id="opt_use_0">
      <option value="1" selected="selected">
        사용함
      </option>
      <option value="0">사용안함</option>
    </select>
  </td>
    `;
  });
};

const addAddtionalOptionList = () => {
  sales_product_additional_options.push({
    option: "",
    item: "",
  });

  let specific_tbody = document.getElementById("addtional_option_table");
  let row = specific_tbody.insertRow(specific_tbody.rows.length);
  var cell1 = row.insertCell(0);

  var cell2 = row.insertCell(1);

  sales_product_additional_options.map((option, key) => {
    cell1.innerHTML = ` <label for="opt${key + 1}_subject">추가${
      key + 1
    }</label>
    <input
        type="text"
        name={opt${key + 1}_subject}
        id={opt${key + 1}_subject}
        class="frm_input"
        size="15"
        />
        `;

    cell2.innerHTML = `<label for="opt${key + 1}"><b>추가${
      key + 1
    } 항목</b></label>
      <input
        type="text"
        name={opt${key + 1}}
        id={opt${key + 1}}
        class="frm_input"
        size="40"
      />
  <button onclick=deleteAddtionalOptionList(${key})>
    삭제
  </<button>`;
  });
};

const deleteAddtionalOptionList = (key) => {
  let specific_tbody = document.getElementById("addtional_option_table");

  sales_product_additional_options.splice(key + 1, 1);

  specific_tbody.children.length === 2
    ? specific_tbody.children[key].remove()
    : specific_tbody.children[key + 1].remove();
};


let typeInfo = document.getElementById("sit_compact_fields");

let category_id = document.getElementById("categoryId");


function selectCategoryChange(event) {
  const categoryId = event.target.value;

  let category_show = document.getElementById("category_show");
}

