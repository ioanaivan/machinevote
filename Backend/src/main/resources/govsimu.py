# govsimu est conçu pour simuler la réponse d'un http serveur gouvernemental
# Il renvoie une réponse si l'utilisateur en recherche existe
# Il identifie également les cas où l'utilisateur est mineur ou non enregistré dans la liste  

from flask import Flask
from flask import request
import requests
from flask import json
import time
import logging
app = Flask(__name__)

# Créer et configurer un logger
# Ceci permet de créer un fichier pour les logs effectués à travers govsimu 
# Option par défaut pour enregistrer et traiter une erreur de processus 
logging.basicConfig(filename="newfile.log",
                    format='%(asctime)s %(message)s',
                    filemode='w',
                    level=logging.INFO)

# Créer un objet logger
logger=logging.getLogger()

# Définir le threshold du logger
logger.setLevel(logging.DEBUG)

# Afficher dans le fichier log un message lors du démarrage de govsimu
logging.info('You are logged in')

# Test de connexion
requests_log = logging.getLogger("requests.packages.urllib3")
requests_log.setLevel(logging.DEBUG)
requests_log.propagate = True
requests.get('https://httpbin.org/headers')

# Recevoir une demande d'authentification de l'électeur
@app.route('/electeur-authentication', methods=['POST'])

# Méthode qui prend les données envoyées par la requête en mode json        
def validate_voter():
    content = request.json
    
# Afficher dans le fichier log les données reçues 
    logger.info(content)

# Cas les champs données sont vides    
    if(not content["CarteID"] or  not content["SecuID"]):
        userMessage={"message":"National card id or Security id is not provided"}
        return userMessage 
# Cas où l'utilisateur est mineur       
    else:
        CarteID = content["CarteID"]
        SecuID = content["SecuID"]
        if(content["CarteID"]=="22222" or content["SecuID"]=="2222"):
            userMessage={"message":"User is Minor"}
            return userMessage
# Cas où l'utilisateur n'est pas enregistré         
        else:
            if(content["CarteID"]=="11111" or content["SecuID"]=="1111"):
                userMessage={"message":"User is not registered in the electoral list"}
                return userMessage  
# Cas où l'utilisateur existe               
            else:
                userMessage={"message":"User found"}
                return userMessage
                

# Envoyer la réponse après avoir exécuter la requête
@app.route('/list-electeur', methods=['GET'])
def listVoters():
    return json.dumps(request)

if __name__ == "__main__":
    app.run(debug=True,host='localhost', port=4000)