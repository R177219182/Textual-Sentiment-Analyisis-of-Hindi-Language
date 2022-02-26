# Sentiment Analysis
Analyzing The Textual Data Of Hindi Movie Reviews

# Methodology
## Step 1: Preprocessing
        a) Sentence Segmentation - Since the sentences have been separated 
           by a dollar sign, we will separate them and store them in a 
           different array
        b) Tokenization
        c) Removal of Stop Words

## Step 2: Feature Extraction
        a) TF-IDF (Term Frequency- Inverse Document Frequency)
           - Compute the TF-IDF score of unigram, bigram and trigram.
           - Make a feature matrix
           - Split the data into training and testing. 
        b) Lexicon Based Approach
           - Compare with Hindi SentiWordNet and find the polarity
              and the POS tag as well.  

## Step 3: Sentiment Score Computation
        a) Machine Learning Algorithms
           - k-Nearest Neighbors
        
        b) Lexicon-Based Approach
           - Use Hindi SentiWordNet(HSWN) to compute the Sentiment Score
           the sentence.