/**
 * 
 */

/**
 * 
 */

function go(url)
{
 window.location = url;
}

function deleteContact(url)
{
 var isOK = confirm("Are you sure to delete customer?");
 if(isOK)
 {
  go(url);
 }
}
