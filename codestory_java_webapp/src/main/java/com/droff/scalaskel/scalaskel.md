## L��choppe de monade sur Scalaskel.

Sur la plan�te Scalaskel, une plan�te en marge de la galaxie, aux confins de l'univers, la monnaie se compte en cents, comme chez nous. 100 cents font un groDessimal. 
Le groDessimal est la monnaie standard utilisable partout sur toutes les plan�tes de l�univers connu. C'est un peu compliqu� � manipuler, 
mais si on ne s'en sert pas y'a toujours des erreurs d'arrondis incroyables quand on les soustrais ou on les divise, c�est idiot, mais c�est comme �a. 
 
Sur Scalaskel, on utilise rarement des groDessimaux, on utilise des pi�ces plus petites :
 Le **Foo** vaut **1 cent**, 
 le **Bar** vaut **7 cents**, 
 le **Qix** vaut **11 cents** et 
 le **Baz** vaut **21 cents**.

Vous tenez une �choppe de monade et autres variables m�ta-syntaxique sur Scalaskel. 
Pour faire face � l�afflux de touristes �trangers avec les poches remplies de groDessimaux vous avez besoin d��crire un programme qui pour toute somme de 1 � 100 cents, 
vous donnera toutes les d�compositions possibles en pi�ces de **Foo**, **Bar**, **Qix** ou **Baz**.

Par exemple, 1 cent ne peut se d�composer qu�en une seule pi�ce Foo.
Par contre 7 cents peuvent se d�composer soit en 7 pi�ces Foo, soit en 1 pi�ce Bar.

### Serveur Web :

Votre serveur doit r�pondre aux requetes http GET de la forme `http://serveur/scalaskel/change/X`, `X` �tant une valeur en cents de 1 � 100 cents.

La r�ponse attendue est un json de la forme :

    [{�foo�: w, �bar�: x, �qix�: y, �baz�: z}, �]
    
Exemples
Pour `http://serveur/scalaskel/change/1` il faut r�pondre :

    [ {�foo�: 1} ]

Pour `http://serveur/scalaskel/change/7` il faut r�pondre :

    [ {�foo�: 7}, {�bar�: 1} ]


L�ordre des valeurs dans le tableau json, ainsi que le formatage n�a pas d�importance � partir du moment ou c�est du json valide, il s�entends.

Bon courage !