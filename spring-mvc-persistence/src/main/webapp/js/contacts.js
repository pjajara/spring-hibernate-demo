/**
 * 
 */

function go(url)
{
 window.location = url;
}

function deleteContact(url)
{
 var isOK = confirm("Are you sure to delete contact?");
 if(isOK)
 {
  go(url);
 }
}
