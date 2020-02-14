/**
 * Created by d.vornicu on 11/02/2019.
 */

var actions = '';

function init(){
    //title
    $("title").text(liste.clients.rest.titre);
    $("h1").text(liste.clients.rest.titre);
    //boutons
    $("#create_bouton").html(fiche.bouton.creer);
    $("#validate_bouton").html(fiche.bouton.valider);
    $("#cancel_bouton").html(fiche.bouton.annuler);
    //fiche(consult, edit, create)
    $('#titleFiche').text(fiche.client.titre.creation);
    $("#lbNom").text(fiche.client.nom);
    $("#lbPrenom").text(fiche.client.prenom);
}
              
function loadClients(){
    //java script with AJAX
    /*            var xhr = new XMLHttpRequest();
     xhr.onload = function(){
     var clients = JSON.parse(this.responseText);
     console.log(clients);
     }

     xhr.open("get", "../../api/clients", true);
     xhr.send();*/

    //JQuery style
    //v1
    /*            $.ajax({
     url : "http://localhost:8090/SampleJavaEE/api/clients",
     dataType : "json",
     success: function(clients){
     console.log(clients);
     }
     });*/
    

    //v2
    $.getJSON("http://localhost:8090/SampleJavaEE/api/clients", function(clients){
        //liste des clients table header
        $('#listeClientsTable').append(
            "<thead>"+
                "<th>" + fiche.client.nom + "</th>"+
                "<th>" + fiche.client.prenom + "</th>"+
                "<th>" + liste.action.titre + "</th>"+
            "</thead>"
        );

        //liste des clients table body
        $('#listeClientsTable').append("<tbody>");
        for (var index in clients) {
            actions =  '<button class="btn btn-primary" onclick="consultClient('+ clients[index].id +');">' + liste.action.consulter + '</button> | ' +
                       '<button class="btn btn-primary" onclick="editClient('+ clients[index].id +');">' + liste.action.modifier + '</button> | ' +
                       '<button class="btn btn-primary" onclick="deleteClient('+ clients[index].id +');">' + liste.action.supprimer + '</button>';
            
            $('#listeClientsTable').append("<tr><td>" + clients[index].nom + "</td><td>" + clients[index].prenom + "</td><td> " + actions + "</td></tr>");
        }
        $('#listeClientsTable').append("</tbody>");
        
    });
}


function createClient(){
    //fiche(create)
    $('#titleFiche').text(fiche.client.titre.creation);
    $("#clientId").val("");
    $("#nom").val("");
    $("#prenom").val("");
    //not read only
    $("#nom").prop("readonly", false);
    $("#prenom").prop("readonly", false);
    $("#validate_bouton").show();
}

function consultClient(clientId){

    $.getJSON("http://localhost:8090/SampleJavaEE/api/clients/" + clientId, function(client){
        //fiche(consult)
        $('#titleFiche').text(fiche.client.titre.consultation(client.prenom, client.nom));

        $("#clientId").val(clientId);
        $("#nom").val(client.nom);
        $("#prenom").val(client.prenom);
        //read only
        $("#nom").prop("readonly", true);
        $("#prenom").prop("readonly", true);
        $("#validate_bouton").hide();
    });
}

function editClient(clientId){
    $.getJSON("http://localhost:8090/SampleJavaEE/api/clients/" + clientId, function(client){
        //fiche(edit)
        $('#titleFiche').text(fiche.client.titre(client.prenom, client.nom));

        $("#clientId").val(clientId);
        $("#nom").val(client.nom);
        $("#prenom").val(client.prenom);
        //not read only
        $("#nom").prop("readonly", false);
        $("#prenom").prop("readonly", false);
        $("#validate_bouton").show();
    });
}


function cancelClient(){
    createClient();
}

function deleteClient(clientId){
    $.getJSON("http://localhost:8090/SampleJavaEE/api/clients/" + clientId + "/delete", function(client){
        window.location.href = 'rsclientjQuery.html';
    });
}

function createOrUpdate(){
    $('#clientForm').validator().on('submit', function (e) {
        if (e.isDefaultPrevented()) {
            // handle the invalid form...
            //alert('err');
        } else {
            // everything looks good!
            var clientId = $("#clientId").val();
            var nom = $("#nom").val();
            var prenom = $("#prenom").val();

            var Data = JSON.stringify({
                "id": clientId,
                "prenom": prenom,
                "nom": nom
            });

            var isCreation = (clientId == "");
            var url;

            if (isCreation) {
                url = "http://localhost:8090/SampleJavaEE/api/clients/new";
            } else {
                url = "http://localhost:8090/SampleJavaEE/api/clients/" + clientId + "/edit"
            }

            $.ajax({
                type: "POST",
                url: url,
                data: Data,
                success: function (data) {
                    window.location.href = "rsclientjQuery.html";
                },
                contentType: "application/json; charset=utf-8",
                dataType: "json"
            });

            $('#clientForm').invalidate();
        }
    });
}
 
