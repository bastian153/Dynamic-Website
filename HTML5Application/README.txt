URL: andromeda-8.ics.uci.edu:22344/index.html

PLEASE NOTE: To connect to this site, you might be inside the school's network
either by being on campus or through VPN. It will not find it otherwise.

The following notes are for the grader:
1. The main index page generates the products randomly through Javascript from
   a file called generatetable.js. There is a comment in the index.html to show
   how the table is structured without the javascript code.

2. There is VERY...VERY minimal use of the Bootstrap Framework. It is only in the 
   form.html and it specifically uses Font Awesome to generate the only four 
   icons that are in the first two section of the form. It is VERY VERY minimal 
   and the rest is done through our own CSS.

3. This website was optimized for Chrome in mind, experience may vary when using
   a different browser such as Internet Explorer, Safari, and Firefox.

4. In case the format of filling out the form is not clear:
    1.) ISBN: Number for the specific book, can be in ISBN-10 or ISBN-13. If you
        write for ISBN-13, you cannot include the hyphen(-).
    2.) Quantity: Must be 1 or greater. Any text or number lower than that will
        not be accepted.
    3.) First and Last name and City must not be empty.
    4.) Address must be a street number, space, followed by street name. Street
        name can consist of multiple words.
    5.) Zip code must be 5 digits long. The optional 4 digits can be added with
        5 digits first, hyphen(-), followed by 4 digits.
    6.) Credit Card number must be 16 digits long. There can be a hyphen(-) that
        separates it every 4 digits, or you can completely ignore the hyphen and
        only have 16 numbers together.
    7.) State, credit card month and year, must be selected and not be at the 
        default value.
    8.) CSV must be 3 digits long.

Valid Form Example:

ISBN: 9780140177398
Quantity: 3

First Name: John
Last Name: Doe
Address: 123 Fake St.
City: Plainsville
State: OH
Zip Code: 93782

Credit Card Number: 1212-0871-1950-5269
Month: Nov
Year: 2016
CSV: 546


5. If anything looks weird or out of place, try refreshing the page or clearing
   the cache.