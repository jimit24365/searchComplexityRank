# searchComplexityRank

Application will fetch results from google search and will rank result on basis of following formula

aboveNormalWords = sentence length - words with two character length 
ComplexityScore = (compound conjunction *0.25)+(complex conjunction * 0.5)+(word larger than 4 character * 0.5) + (words larger than 6 character * 1) + ((aboveNormalWords > 8) *0.25)

Credits :
81813780/AVLoadingIndicatorView --> Awesome loading indicator
