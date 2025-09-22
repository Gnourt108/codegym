class Book {
    constructor(id, category, title, author, year, nation, age, quantity) {
        this.id = id;
        this.category = category;
        this.title = title;
        this.author = author;
        this.year = year;
        this.nation = nation;
        this.age = age;
        this.quantity = quantity;
    }
}

class Borrower {
    constructor(id, name, phone, email, bookId, title, quantity, borrowDate, returnDate, status) {
        this.id = id;
        this.name = name;
        this.phone = phone;
        this.email = email;
        this.bookId = bookId;
        this.title = title;
        this.quantity = quantity;
        this.borrowDate = borrowDate;
        this.returnDate = returnDate;
        this.status = status;
    }
}

let books = [];
let borrowBooks = [];
let editBookId = null;

// Add Book
document.getElementById("btnAdd").addEventListener("click", () => {
    const category = document.getElementById("category").value;
    const title = document.getElementById("title").value;
    const author = document.getElementById("author").value;
    const year = parseInt(document.getElementById("year").value);
    const nation = document.getElementById("nation").value;
    const age = parseInt(document.getElementById("age").value);
    const quantity = parseInt(document.getElementById("quantity").value) || 0;

    if (!title) {
        alert("Bạn chưa nhập tiêu đề sách");
        return;
    }
    if (!author) {
        alert("Bạn chưa nhập tác giả");
        return;
    }
    if (isNaN(year)) {
        alert("Vui lòng nhập số cho năm");
        return;
    }
    if (!nation) {
        alert("Bạn chưa nhập quốc gia");
        return;
    }
    if (isNaN(age)) {
        alert("Bạn chưa nhập độ tuổi");
        return;
    }
    if (isNaN(quantity) || quantity < 0) {
        alert("Vui lòng nhập số lượng hợp lệ");
        return;
    }

    if (editBookId !== null) {
        if(!confirm("Bạn có chắc chắn muốn lưu thay đổi cho sách này?")) return;
        const book = books.find(b => b.id === editBookId);
        if (!book) {
            alert("Không tìm thấy sách để sửa");
            editBookId = null;
            document.getElementById("btnAdd").textContent = "Thêm";
            return;
        }

        book.category = category;
        book.title = title;
        book.author = author;
        book.year = year;
        book.nation = nation;
        book.age = age;
        book.quantity = quantity;

        displayTable();
        document.getElementById("addBookForm").reset();
        editBookId = null;
        document.getElementById("btnAdd").textContent = "Thêm"; // reset text nút
        return;
    }
    else{
        if(!confirm("Bạn có muốn thêm sách mới?")) return;
        const id = Date.now();
        books.push(new Book(id, category, title, author, year, nation, age, quantity));
        displayTable();
        document.getElementById("addBookForm").reset();
    }
});

// Update Book
document.getElementById("btnUpdate").addEventListener("click", () => {
    if (editBookId === null) {
        alert("Bạn chưa chọn sách để sửa!");
        return;
    }

    const category = document.getElementById("category").value;
    const title = document.getElementById("title").value;
    const author = document.getElementById("author").value;
    const year = parseInt(document.getElementById("year").value);
    const nation = document.getElementById("nation").value;
    const age = parseInt(document.getElementById("age").value);
    const quantity = parseInt(document.getElementById("quantity").value);

    let book = books.find(b => b.id === editBookId);
    if (!book) {
        alert("Không tìm thấy sách để sửa");
        return;
    }

    if (!title) {
        alert("Bạn chưa nhập tiêu đề sách");
        return;
    }
    if (!author) {
        alert("Bạn chưa nhập tác giả");
        return;
    }
    if (isNaN(year)) {
        alert("Vui lòng nhập số cho năm");
        return;
    }
    if (!nation) {
        alert("Bạn chưa nhập quốc gia");
        return;
    }
    if (isNaN(age)) {
        alert("Bạn chưa nhập độ tuổi");
        return;
    }
    if (isNaN(quantity) || quantity < 0) {
        alert("Vui lòng nhập số lượng hợp lệ");
        return;
    }
    if(!confirm("Bạn có muốn sửa sách?")) return;

    book.category = category;
    book.title = title;
    book.author = author;
    book.year = year;
    book.nation = nation;
    book.age = age;
    book.quantity = quantity;

    displayTable();
    document.getElementById("addBookForm").reset();
    editBookId = null;
});

// xóa book
document.getElementById("btnDelete").addEventListener("click", () => {
    if (editBookId === null) {
        alert("Vui lòng chọn sách để xóa bằng cách nhập ID sách");
        return;
    }
    if(!confirm("Bạn có muốn xóa sách?")) return;
    books = books.filter(b => b.id !== editBookId);
    editBookId = null;
    displayTable();
    document.getElementById("addBookForm").reset();
});


function displayTable() {
    const title = document.getElementById("filterTitle").value.toLowerCase();
    const category = document.getElementById("filterCategory").value;
    const author = document.getElementById("filterAuthor").value.toLowerCase();
    const nation = document.getElementById("filterNation").value.toLowerCase();
    const min = parseInt(document.getElementById("filterMin").value);
    const max = parseInt(document.getElementById("filterMax").value);

    let tbody = document.querySelector("#bookTable tbody");
    tbody.innerHTML = "";

    books
        .filter(b => {
            let ok = true;
            if (title) ok = ok && b.title.toLowerCase().includes(title);
            if (category) ok = ok && b.category === category;
            if (nation) ok = ok && b.nation.toLowerCase().includes(nation);
            if (author) ok = ok && b.author.toLowerCase().includes(author);
            if (!isNaN(min)) ok = ok && b.quantity >= min;
            if (!isNaN(max)) ok = ok && b.quantity <= max; 
            return ok;
        })
        .forEach((book, index) => {
            let tr = document.createElement("tr");
            if (book.quantity <= 5) tr.classList.add("warning");

            tr.innerHTML = `
                <td>${index + 1}</td>
                <td>${book.id}</td>
                <td>${book.title}</td>
                <td>${book.category}</td>
                <td>${book.author}</td>
                <td>${book.year}</td>
                <td>${book.nation}</td>
                <td>${book.age}</td>
                <td>${book.quantity}</td>
                <td>
                    <button onclick="editBook(${book.id})">Chỉnh sửa</button>
                    <button onclick="deleteBook(${book.id})">Xóa</button>
                </td>
            `;
            tbody.appendChild(tr);
        });
}

// Edit Book
function editBook(id) {
    const book = books.find(b => b.id === id);
    if (!book) {
        alert("Không tìm thấy sách với ID: " + id);
        return;
    }

    document.getElementById("category").value = book.category;
    document.getElementById("title").value = book.title;
    document.getElementById("author").value = book.author;
    document.getElementById("year").value = book.year;
    document.getElementById("nation").value = book.nation;
    document.getElementById("age").value = book.age;
    document.getElementById("quantity").value = book.quantity;

    editBookId = id;
    document.getElementById("btnAdd").textContent = "Lưu thay đổi";
}

// Delete Book
function deleteBook(id) {
    if (!confirm("Bạn có chắc muốn xóa sách này?")) return;
    books = books.filter(b => b.id !== id);
    displayTable();
}

// Borrow Book
document.getElementById("btnBorrow").addEventListener("click", () => {
    const id = Date.now();
    const name = document.getElementById("borrowerName").value.trim();
    const phone = document.getElementById("borrowerNumber").value.trim();
    const email = document.getElementById("borrowerEmail").value.trim();
    const bookId = parseInt(document.getElementById("borrowBookId").value);
    const quantity = parseInt(document.getElementById("borrowBookQuantity").value) || 0;
    const borrowDate = document.getElementById("borrowDate").value;
    const returnDate = document.getElementById("returnDate").value;

    if (!name) {
        alert("Vui lòng nhập tên người mượn");
        return;
    }
    if (!phone) {
        alert("Vui lòng nhập số điện thoại người mượn");
        return;
    }
    if (!bookId) {
        alert("Vui lòng nhập ID sách");
        return;
    }
    if (isNaN(quantity) || quantity <= 0) {
        alert("Vui lòng nhập số lượng hợp lệ");
        return;
    }
    if (!borrowDate || !returnDate) {
        alert("Vui lòng nhập ngày mượn và ngày trả dự kiến");
        return;
    }
    if (new Date(borrowDate) > new Date(returnDate)) {
        alert("Ngày trả dự kiến phải sau ngày mượn");
        return;
    }

    const book = books.find(b => b.id === bookId);
    if (!book) {
        alert("Không tìm thấy sách với ID: " + bookId);
        return;
    }
    if (book.quantity < quantity) {
        alert(`Sách "${book.title}" (ID: ${book.id}) hiện chỉ còn ${book.quantity} quyển!`);
        return;
    }
    if(!confirm(`Bạn có chắc muốn mượn ${quantity} quyển "${book.title}"`)) return;

    // Update book quantity and add borrower
    book.quantity -= quantity;
    borrowBooks.push(new Borrower(id, name, phone, email, bookId, book.title, quantity, borrowDate, returnDate, "Đang mượn"));

    displayTable();
    displayBorrowTable();
    document.getElementById("borrowForm").reset();
});

// Return Book
document.getElementById("btnReturn").addEventListener("click", () => {
    const borrowerName = document.getElementById("borrowerName").value.trim();
    const bookId = parseInt(document.getElementById("borrowBookId").value);

    if (!borrowerName || isNaN(bookId)) {
        alert("Vui lòng nhập tên người mượn và ID sách");
        return;
    }

    const borrower = borrowBooks.find(b => b.bookId === bookId && b.name.toLowerCase() === borrowerName.toLowerCase() && b.status === "Đang mượn");
    if (!borrower) {
        alert("Không tìm thấy thông tin mượn sách với tên và ID sách này");
        return;
    }

    if(!confirm("Bạn có muốn trả sách ?")) return;

    const book = books.find(b => b.id === bookId);
    if (book) {
        book.quantity += borrower.quantity;
    }

    borrower.status = "Đã trả";

    displayTable();
    displayBorrowTable();
    document.getElementById("borrowForm").reset();
});

// Display Borrow Table
function displayBorrowTable() {
    const filterBorrowerID = document.getElementById("filterBorrowerID").value;
    const filterBorrowerName = document.getElementById("filterBorrowerName").value.toLowerCase();
    const filterBorrowerNumber = document.getElementById("filterBorrowerNumber").value;
    const filterBorrowerEmail = document.getElementById("filterBorrowerEmail").value.toLowerCase();
    const filterBorrowBook = document.getElementById("filterBorrowBook").value.toLowerCase();
    const filterStatus = document.getElementById("filterBorrowStatus").value;
    const filterBorrowDate = document.getElementById("filterBorrowDate").value;
    const filterReturnDate = document.getElementById("filterReturnDate").value;

    let tbody = document.querySelector("#borrowTable tbody");
    tbody.innerHTML = "";

    borrowBooks
        .filter(b => {
            let ok = true;
            if (filterBorrowerID) ok = ok && b.id.toString().includes(filterBorrowerID);
            if (filterBorrowerName) ok = ok && b.name.toLowerCase().includes(filterBorrowerName);
            if (filterBorrowerNumber) ok = ok && b.phone.includes(filterBorrowerNumber);
            if (filterBorrowerEmail) ok = ok && b.email.toLowerCase().includes(filterBorrowerEmail);
            if (filterBorrowBook) ok = ok && b.title.toLowerCase().includes(filterBorrowBook);
            if (filterStatus) ok = ok && b.status.toLowerCase() === filterStatus.toLowerCase();
            if (filterBorrowDate) ok = ok && b.borrowDate === filterBorrowDate;
            if (filterReturnDate) ok = ok && b.returnDate === filterReturnDate;
            return ok;
        })
        .forEach((b, index) => {
            let tr = document.createElement("tr");
            tr.innerHTML = `
                <td>${index + 1}</td>
                <td>${b.id}</td>
                <td>${b.phone}</td>
                <td>${b.email}</td>
                <td>${b.name}</td>
                <td>${b.bookId}</td>
                <td>${b.title}</td>
                <td>${b.quantity}</td>
                <td>${b.borrowDate}</td>
                <td>${b.returnDate}</td>
                <td>${b.status}</td>
            `;
            tbody.appendChild(tr);
        });
}