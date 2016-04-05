function main() {
	console.log("Hello World!");
	var bookArray = createBookArray();
	createTable(bookArray);
}



function createBookArray(){
	var bookArray = [new Book("Ender's Game", "Orson Scott Card", "0812550706", "5.99", "endersgame")];
	bookArray.push(new Book("The Gunslinger", "Stephen King", "1501143514", "14.30", "gunslinger"));
	bookArray.push(new Book("1984", "George Orwell", "8499890946", "10.00", "1984"));
	bookArray.push(new Book("Night", "Elie Wiesel", "0374500010", "7.50", "night"));
	bookArray.push(new Book("Lord of the Flies", "William Golding", "0399501487", "8.49", "lordoftheflies"));
	bookArray.push(new Book("Fahrenheit 451", "Ray Bradbury", "1451673310", "9.52", "fahrenheit451"));
	bookArray.push(new Book("Of Mice and Men", "John Steinbeck", "0140177396", "7.00", "ofmiceandmen"));
	bookArray.push(new Book("To Kill A Mockingbird", "Harper Lee", "0446310786", "5.89", "tokillamockingbird"));
	bookArray.push(new Book("Like Water for Chocolate", "Laura Esquirel", "038542017X", "8.92", "likewaterforchocolate"));
	bookArray.push(new Book("The Giver", "Lois Lowry", "0544336267", "4.39", "thegiver"));
	bookArray.push(new Book("Lost in Shangri-La", "Mitchell Zuckoff", "0061988359", "10.66", "lostinshangrila"));
	bookArray.push(new Book("Harry Potter and the Cursed Child, Parts I & II", "JK Rowling", 
		"1338099132", "17.99", "harrypotterandthecursedchild"));
	return shuffle(bookArray);
}



function createTable(bookArray){
	var html = "<table class=\"col-12\">\n";
	html += createRows(bookArray);
	html += "</table>\n";
	document.getElementById("products").innerHTML = html;
	console.log(html);
}


function createRows(bookArray){
	var html = "";
	var rows = Math.floor(bookArray.length / 3);
	var index = 0;
	for(var i = 0; i < rows; i++){
		html += "<tr>\n";
		for(var j = 0; j < 3 && index < bookArray.length; j++){
			var book = bookArray[index];
			html += "<td class=\"col-4\">\n";
			html += "<a href=\"products/" + book.file + ".html\">\n";
			html += "<img src=\"./img/" + book.file + ".jpg\" alt=\"" 
				+ book.name + "\">\n";
			html += "<p>" + book.name + "</p>\n";
			html += "<p>Author: " + book.author + "</p>\n";
			html += "<p>ISBN-10: " + book.isbn + "</p>\n";
			html += "<p>$" + book.price + "</p>\n";
			index += 1;
		}
		html += "</tr>\n";
	}
	return html;
}



/**
	Constructor for Book Object
	name - Name of the Book
	author - Name of author
	isbn - ISBN for the book
	price - Price of the book
	file - name of the file for the html and image
*/
function Book(name, author, isbn, price, file){
	this.name = name;
	this.author = author;
	this.isbn = isbn;
	this.price = price;
	this.file = file;
}



function shuffle(array) {
  var currentIndex = array.length, temporaryValue, randomIndex;

  // While there remain elements to shuffle...
  while (0 !== currentIndex) {

    // Pick a remaining element...
    randomIndex = Math.floor(Math.random() * currentIndex);
    currentIndex -= 1;

    // And swap it with the current element.
    temporaryValue = array[currentIndex];
    array[currentIndex] = array[randomIndex];
    array[randomIndex] = temporaryValue;
  }

  return array;
}