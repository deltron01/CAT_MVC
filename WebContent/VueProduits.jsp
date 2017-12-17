<%@page import = "web.ProduitModel" %>
<%@page import = "metier.Produit" %>
<%@page import = "java.util.Iterator" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>App Web classique</title>
<link rel="stylesheet" type="text/css" href="css/style.css">
<script type="text/javascript">
   function confirmer(url){
	   var rep = confirm("Etes-vous sûr de vouloir supprimer définitivement cette donnée ?");
	   if(rep == true){
		   document.location = url;
	   }
   }
</script>
</head>
<body>
   <div>
     <form action="controleur.php" method="post">
       <table>
         <tr>
           <td>Mot clé : </td>
           <td><input type="text" name="motcle" value=""/></td>
           <td><input type = "hidden" name = "action" value = "Chercher" /></td>
           <td><input type="submit" value="chercher"/></td>
         </tr>
       </table>
     </form>
   </div>
   <div>
     <form action="save.php" method="post">
       <table>
         <tr>
           <td>Réference : </td>
           <td><input type="text" name="reference" value="${model1.produit.reference }"/></td>
           <td></td>
         </tr>
         <tr>
           <td>Désignation : </td>
           <td><input type="text" name="designation" value="${model1.produit.designation }"/></td>
           <td></td>
         </tr>
         <tr>
           <td>Prix : </td>
           <td><input type="text" name="prix" value="${model1.produit.prix }"/></td>
           <td></td>
         </tr>
         <tr>
           <td>Quantité : </td>
           <td><input type="text" name="quantite" value="${model1.produit.quantite }"/></td>
           <td></td>
         </tr>
         <tr>
          <td><input type="submit" name= "action" value = "save" /></td>
          <td><input type="hidden" name="mode" value="${model1.mode }"/></td>
         </tr>
       </table>
     </form>
   </div>
   <div>
      ${model1.errors }
   </div>
   <br/>
   <div>
   <%
         ProduitModel produits;

         if(request.getAttribute("model1") == null)
         {
    
         }
         else{
    	produits = (ProduitModel) request.getAttribute("model1"); %>
     <table border="1" class="table1">
         <tr>
           <th>Designation</th>
           <th>Prix</th>
           <th>Quantité</th>
         </tr>
        <% 
        Iterator<Produit> list = produits.getProduits().iterator();
        while(list.hasNext())
		{
			Produit pr = list.next(); %> 
	  
	      <tr>
           <td><%=pr.getDesignation() %> </td>
           <td><%=pr.getPrix() %> </td>
           <td> <%=pr.getQuantite() %></td>
           <td>
             <form action = "javascript:confirmer('delete.php?action=Supprimer&ref=<%=pr.getReference() %>')" method = "post">
               <input type = "hidden" name = "ref" value = "<%=pr.getReference() %>" />
               <input type = "hidden" name = "action" value = "Supprimer" />
               <input type = "submit" value = "<%=pr.getReference() %>" />
             </form>
           </td>
           <td><a href="delete.php?action=edit&ref=<%=pr.getReference() %>">Edit</a></td>
          </tr>
	  <% }}%>
     
     </table>
   </div>
</body>
</html>