## StackSpot Plugin
# Pequeno Investidor :moneybag:
<h4 align="center"> 
	🚧  Pequeno Investidor 👨‍🏫 em construção ✔️ 🚧
</h4>

## Jinja
## :pencil2: Descrição 
Esse projeto tem como objetivo fornecer um conjunto de endpoints para consultas sobre fundos imobiliarios e ações listados na bolsa brasileira:moneybag:.
* Preço atual.
* Dividend Yield.
* Ultimo rendimento mensal.
* Calculo de rendimento com base no valor aplicado.
* Outras informações uteis para futuros investidores.

You can use jinja to make a template-data folder more dynamic.
## 💻Ideia do Projeto
```bash
Utilizar Web Scraping para fazer a raspagem de informações de sites brasileiros e fornecer essas informações via API, sem 
necessidade de consultar essas informações por orgãos como CVM, CEI, mas obtendo essas informações de portais com registros 
nos respectivos orgãos.
- Principais portais utilizados: Status Invest, Suno, Fundsexplorer.
- Informações disponibilizadas por meio de uma API Rest.
- Raspagem de informações feitas utilizando jsoup e Spring.
```
 ## 👷Como rodar

complete documentation of jinja: https://jinja.palletsprojects.com/en/3.0.x/templates/
```bash
# Clonar o repositório
git clone https://github.com/marlo2222/pequenoinvestidor
# Entrar no diretório
# Baixar as dependências
mvn install
# Executar o servidor
mvn spring-boot:run
```
Feito isso, abra o seu navegador e acesse `http://localhost:8080/`

### Example Inputs:
- Resource: 
- Method: ## 🤔 Como contribuir <br/>

- Faça um fork desse repositório; <br/>
- Cria uma branch com a sua feature: `git checkout -b minha-feature`;<br/>
- Faça commit das suas alterações: `git commit -m 'feat: Minha nova feature'`; <br/>
- Faça push para a sua branch: `git push origin minha-feature`.<br/>
<br/>
Depois que o merge da sua pull request for feito, você pode deletar a sua branch. <br/>

## Licença 

MIT License

Copyright (c) 2020 Marlo Henrique


