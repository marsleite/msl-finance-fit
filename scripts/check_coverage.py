import re
import sys

# Caminho do arquivo HTML gerado
caminho_arquivo = "./build/reports/jacoco/test/html/index.html"

# Abre o arquivo e lê o conteúdo
with open(caminho_arquivo, 'r') as arquivo:
    conteudo = arquivo.read()

# Procura o quarto valor "100%" usando expressões regulares
padrao = r'<span class="percent">\s*(.*?)\s*</span>'
ocorrencias = re.findall(padrao, conteudo)

# Verifica se existem pelo menos 4 ocorrências
if len(ocorrencias) >= 4:
    valor_percentual = ocorrencias[3].replace('%', '')  # Remove o símbolo de percentagem, se presente
    valor_percentual = float(valor_percentual)

    # Verifica se o valor é menor que 90%
    if valor_percentual < 90:
        print("Erro: O valor percentual é menor que 90%.")
        sys.exit(1)
    else:
        print("Valor percentual: ", valor_percentual)
else:
    print("Quarta ocorrência de <span class='percent'> não encontrada.")

