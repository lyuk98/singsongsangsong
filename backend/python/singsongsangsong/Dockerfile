FROM python:3.11

WORKDIR /app
COPY . /app

RUN pip install pipenv
RUN pipenv install

EXPOSE 8000

CMD ["pipenv", "run", "gunicorn", "main:app", "--workers", "4", "--worker-class", "uvicorn.workers.UvicornWorker", "--bind", "0.0.0.0:8000"]
