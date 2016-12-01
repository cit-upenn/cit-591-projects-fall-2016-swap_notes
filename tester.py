from nltk.tokenize import sent_tokenize, word_tokenize
# find by paragraph or sentences
example_text = "Hello Mr. Smith, how are you doing today? The weather is great and Python is awesome. The sky is pinkish-blue. You should not eat cardboard."

print(sent_tokenize(example_text))
print(word_tokenize(example_text))
