![image](https://user-images.githubusercontent.com/97681752/185422155-9574bafc-cdc8-4f43-b2a3-7cac12bee8d8.png)
# Alura Backend Challenge 

<h2 id="objetivo"> Objetivo </h2>
Desenvolver uma API REST backend do zero, aplicando regras de negócio fornecidas pela Alura.

## Endpoints disponíveis

### Não precisa de autenticação.
- GET /videos/free
> Lista todos os vídeos atualmente cadastrados.

### Precisa de autenticação.

- GET /videos
> Lista todos os vídeos atualmente cadastrados.

- GET /videos/{id}
> Lista detalhes do vídeo com o ID escolhido.

#
- POST /videos
> Cadastra um vídeos, desde que as informações fornecidas sejam válidas. 
##### Exemplo:

``` 
{
    "titulo": "Exemplo do título",
    "descricao": "Exemplo de descrição",
    "url": "https://www.youtube.com/watch?v=exemploDeUrlVálida",
    "categoriaId": 3
} 
```
#

- PUT /videos/{id}
> Atualiza informações do vídeos cadastrado como ID escolhido.
##### Exemplo: 

```
{
    "titulo": "Exemplo do título2",
    "descricao": "Exemplo de descrição2",
    "url": "https://www.youtube.com/watch?v=exemploDeUrlVálida2",
    "categoriaId": 2
}

```

#
- DEL /videos/{id}
> Deleta o video com o ID escolhido.

- GET /categorias
> Lista todas as categorias cadastradas atualmente.

- GET /categorias/{id}
> Lista detalhes da categoria com o ID escolhido.

- GET /categorias/{id}/videos
> Lista todos os vídeos cadastrados cuja categoria possui o ID escolhido.

- POST /categorias
> Cadastra uma nova categoria.
#
- PUT /categorias/{id}
> Atualiza informações da categoria como ID escolhido. 
##### Exemplo: 
```
{
    "titulo": "Exemplo de título",
    "cor": "ROXO"
}

```
##### Cores disponíveis:
######     VERMELHO, VERDE, ROXO, AMARELO, AZUL, LARANJA;
#
- DEL /cageorias/{id}
> Deleta o video com o ID escolhido.
#

## Autenticação

- POST /auth
> Use o tipo e o token gerado para se autenticar.

```
{
    "email": "user@email.com",
    "senha": "password"
}
```

##### Resultado:

![image](https://user-images.githubusercontent.com/97681752/185518179-e0f1fa6e-f039-4267-90cf-709472cb2ffa.png)


<img src="https://user-images.githubusercontent.com/79534537/130516084-f199b740-4daf-4d67-a995-9bfdb2bd4560.png" alt="aluraflix" width="250"/>

