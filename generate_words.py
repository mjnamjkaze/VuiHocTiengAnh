import urllib.request
import json
import urllib.parse
import os
import time

def translate_batch(words):
    text = "\n".join(words)
    url = f"https://translate.googleapis.com/translate_a/single?client=gtx&sl=en&tl=vi&dt=t&q={urllib.parse.quote(text)}"
    try:
        req = urllib.request.Request(url, headers={'User-Agent': 'Mozilla/5.0'})
        response = urllib.request.urlopen(req)
        data = json.loads(response.read().decode('utf-8'))
        
        results = []
        for segment in data[0]:
            if segment[0]:
                results.append(segment[0].replace('\n', '').strip())
        return results
    except Exception as e:
        print("Error translating:", e)
        return ["Nghia cua tu: " + w for w in words]

def get_top_words(limit=2000):
    url = "https://raw.githubusercontent.com/first20hours/google-10000-english/master/google-10000-english-no-swears.txt"
    req = urllib.request.Request(url, headers={'User-Agent': 'Mozilla/5.0'})
    response = urllib.request.urlopen(req)
    text = response.read().decode('utf-8')
    words = text.split('\n')
    
    # Filter short or non-alpha words
    valid_words = [w.strip() for w in words if len(w.strip()) > 1 and w.strip().isalpha()]
    return valid_words[:limit]

def main():
    print("Fetching top 2000 words...")
    words = get_top_words(2000)
    
    out_dir = r"d:\VuiHocTiengAnh\app\src\main\assets"
    os.makedirs(out_dir, exist_ok=True)
    
    batch_size = 50
    all_data = []
    
    print("Translating words in batches...")
    for i in range(0, len(words), batch_size):
        batch_words = words[i:i+batch_size]
        print(f"Translating batch {i // batch_size + 1} / {len(words) // batch_size}...")
        meanings = translate_batch(batch_words)
        
        # Fallback if mismatch
        if len(meanings) != len(batch_words):
            meanings = ["Nghia cua: " + w for w in batch_words]
            
        for w, m in zip(batch_words, meanings):
            idx = len(all_data)
            level = "A1" if idx < 500 else "A2" if idx < 1000 else "B1" if idx < 1500 else "B2"
            
            entry = {
                "id": 5000 + idx,
                "w": w,
                "p": f"/{w}/",
                "m": m,
                "s": "word",
                "l": level,
                "t": "common",
                "e": [
                    f"I need to learn the word {w}.",
                    f"Can you spell {w} for me?"
                ]
            }
            all_data.append(entry)
        time.sleep(0.5) # limit rate
        
    print(f"Successfully generated {len(all_data)} words.")
    
    # Split into 8 files of 250 words
    file_prefix = ["words_a1_1.json", "words_a1_2.json", "words_a2_1.json", "words_a2_2.json", 
                   "words_b1_1.json", "words_b1_2.json", "words_b2_1.json", "words_b2_2.json"]
    
    for i in range(8):
        start = i * 250
        end = start + 250
        chunk = all_data[start:end]
        
        path = os.path.join(out_dir, file_prefix[i])
        with open(path, "w", encoding="utf-8") as f:
            json.dump(chunk, f, ensure_ascii=False)
        print(f"Saved {file_prefix[i]} with {len(chunk)} words.")

if __name__ == "__main__":
    main()
