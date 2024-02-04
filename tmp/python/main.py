from fastapi import FastAPI
from pydantic import BaseModel

app = FastAPI()


@app.get("/print")
def printHello():
    return "Hello, World!!"


class Item(BaseModel):
    name: str
    description: str = None


@app.post("/items/")
async def create_item(item: Item):
    print(item)
    return {"name": item.name, "description": item.description}
