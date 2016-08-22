import os
from gensim import utils


def extract_json_pages(filename, filter_namespaces=False):
     with utils.smart_open(filename) as fin:
            for line in fin:
                document = json.loads(line.strip())

                title = document['wikiTitle']
                text = ' '.join(document['sentences'])
                wiki_id = str(document['wikiId'])

                yield title, text, wiki_id


def absolute_path(path_from_latent_gensim):
	WORKING_DIR = os.path.dirname(os.path.abspath(__file__))
	return os.path.join(WORKING_DIR, path_from_latent_gensim)


WIKI_CORPUS = absolute_path('../../resources/wikipedia/wikipedia-w2v-linkCorpus.json.gz')
WIKI_LINKS = absolute_path('../../resources/wikipedia/wiki-links-sorted.gz')

GENSIM_DIR = absolute_path('../../../../data/processing/wikipedia/latent/gensim')

WIKI_FILENAME = os.path.join(GENSIM_DIR, 'corpus/make_wiki_')
WIKI_LDA_DIR = absolute_path('../../../../data/processing/wikipedia/latent/gensim/wiki_lda')
WIKI_SVD_DIR = absolute_path('../../../../data/processing/wikipedia/latent/svd')
